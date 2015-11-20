package kimxu.adapter;

import android.view.ViewGroup;


public abstract class AssemblyItemFactory<ITEM extends AssemblyItem>{
    private int itemType;
    private Class<?> beanClass;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    /**
     * 用来判断一个类Class1和另一个类Class2是否相同或是另一个类的子类或接口。
     * @param itemObject
     * @return
     */
    public boolean isAssignableFrom(Object itemObject){
        if(beanClass == null){
            beanClass = getBeanClass();
        }
        if(itemObject == null){
            return beanClass == null;
        }
        if(beanClass == null){
            return false;
        }
        Class<?> targetClass = itemObject.getClass();
        return targetClass.isAssignableFrom(getBeanClass());
    }

    public abstract Class<?> getBeanClass();

    /**
     * 创建item
     * @param parent
     * @return
     */
    public abstract ITEM createAssemblyItem(ViewGroup parent);
}
