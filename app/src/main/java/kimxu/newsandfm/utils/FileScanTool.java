package kimxu.newsandfm.utils;

import java.io.File;
import java.util.Vector;

/**
 * 文件过滤扫描类
 * 现在使用 MediaUtils
 */
@Deprecated
public class FileScanTool {

    private Vector<File> filelists = new Vector<File>();

    /**
     * @param rootPath   根目录
     * @param filterName 扫描文件后缀
     */
    public void scanFile(File rootPath, final String filterName) {
        rootPath.listFiles(pathname -> {
            if (((pathname + "").toLowerCase()).endsWith(filterName)) {
                filelists.addElement(pathname);
                return true;
            }
            if (pathname.isDirectory()) {//如果是目录
                scanFile(pathname, filterName);
                return true;
            } else {
                return false;
            }
        });
    }

    /**
     * 多格式扫描
     *
     * @param rootPath    根目录
     * @param filterNames 格式数组
     */
    public void scanFile(File rootPath, final String[] filterNames) {
        for (int i = 0; i < filterNames.length; i++) {
            scanFile(rootPath, filterNames[i]);
        }
    }

    public Vector<File> getFilelists() {
        return filelists;
    }

}  