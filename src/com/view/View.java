package com.view;

import java.util.Scanner;

/**
 * 抽象类View，为所有视图提供继承。并在内部添加Scanner类，以提供子类键入值。
 * @author Administrator
 */
public abstract class View {
    protected Scanner mScanner;
    protected View mView;

    public View() {
        if (mScanner == null){
            mScanner = new Scanner(System.in);
        }
    }
    public abstract View showView();
}
