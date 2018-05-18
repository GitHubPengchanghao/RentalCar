package com.Test;

import com.view.FirstView;
import com.view.View;

public class Test {
    public static void main(String[] args) {
        View nextView = new FirstView();
        while(nextView != null){
            nextView = nextView.showView();
        }
        System.out.println("\n系统正在退出!");
    }
}
