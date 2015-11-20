package kimxu.api.net.utils;


/**
 * Some common string manipulation utilities.
 *
 */
public final class VolleyStringUtil {
    public static final String WHITE_SPACES = " \r\n\t\u3000\u00A0\u2007\u202F";
    public static final String LINE_BREAKS = "\r\n";
    private static char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String bytesToHexString(final byte[] bytes,
                                          Character delimiter) {
        StringBuffer hex = new StringBuffer(bytes.length
                * (delimiter == null ? 2 : 3));
        int nibble1, nibble2;
        for (int i = 0; i < bytes.length; i++) {
            nibble1 = (bytes[i] >>> 4) & 0xf;
            nibble2 = bytes[i] & 0xf;
            if (i > 0 && delimiter != null)
                hex.append(delimiter.charValue());
            hex.append(hexChars[nibble1]);
            hex.append(hexChars[nibble2]);
        }
        return hex.toString();
    }

    /**
     * This is a both way strip
     *
     * @param str
     *            the string to strip
     * @param left
     *            strip from left
     * @param right
     *            strip from right
     * @param what
     *            character(s) to strip
     * @return the stripped string
     */
    public static String megastrip(String str, boolean left, boolean right,
                                   String what) {
        if (str == null) {
            return null;
        }

        int limitLeft = 0;
        int limitRight = str.length() - 1;

        while (left && limitLeft <= limitRight
                && what.indexOf(str.charAt(limitLeft)) >= 0) {
            limitLeft++;
        }
        while (right && limitRight >= limitLeft
                && what.indexOf(str.charAt(limitRight)) >= 0) {
            limitRight--;
        }

        return str.substring(limitLeft, limitRight + 1);
    }

    /**
     * lstrip - strips spaces from left
     *
     * @param str
     *            what to strip
     * @return String the striped string
     */
    public static String lstrip(String str) {
        return megastrip(str, true, false, WHITE_SPACES);
    }

    /**
     * rstrip - strips spaces from right
     *
     * @param str
     *            what to strip
     * @return String the striped string
     */
    public static String rstrip(String str) {
        return megastrip(str, false, true, WHITE_SPACES);
    }

    /**
     * strip - strips both ways
     *
     * @param str
     *            what to strip
     * @return String the striped string
     */
    public static String strip(String str) {
        return megastrip(str, true, true, WHITE_SPACES);
    }

    /**
     * Give me a string and a potential prefix, and I return the string
     * following the prefix if the prefix matches, else null. Analogous to the
     * c++ functions strprefix and var_strprefix.
     */
    public static String stripPrefix(String str, String prefix) {
        return str.startsWith(prefix) ? str.substring(prefix.length()) : null;
    }

    /**
     * Case insensitive version of stripPrefix. Analogous to the c++ functions
     * strcaseprefix and var_strcaseprefix.
     */
    public static String stripPrefixIgnoreCase(String str, String prefix) {
        if (str.length() >= prefix.length()
                && str.substring(0, prefix.length()).equalsIgnoreCase(prefix)) {
            return str.substring(prefix.length());
        }

        return null;
    }

    /**
     * <p>
     * Checks if a String is not empty ("") and not null.
     * </p>
     *
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param s
     *            the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    /**
     * Helper function for null and empty string testing.
     *
     * @return true iff s == null or s.equals("");
     */
    public static boolean isEmpty(String s) {
        return makeSafe(s).length() == 0;
    }

    /**
     * Helper function for null, empty, and whitespace string testing.
     *
     * @return true if s == null or s.equals("") or s contains only whitespace characters.
     */
    public static boolean isEmptyOrWhitespace(String s) {
        s = makeSafe(s);
        for (int i = 0, n = s.length(); i < n; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper function for making null strings safe for comparisons, etc.
     * @return (s == null) ? "" : s;
     */
    public static String makeSafe(String s) {
        return (s == null) ? "" : s;
    }
}
