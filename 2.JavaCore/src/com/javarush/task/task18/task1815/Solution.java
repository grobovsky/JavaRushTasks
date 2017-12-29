package com.javarush.task.task18.task1815;

import java.util.List;

/* 
Таблица
*/

public class Solution {
    public class TableInterfaceWrapper implements ATableInterface {
        private ATableInterface at;

        public TableInterfaceWrapper (ATableInterface at){
            this.at = at;
        }

        @Override
        public void setModel(List rows) {
            System.out.println(rows.size());
            at.setModel(rows);
        }

        @Override
        public String getHeaderText() {
            return at.getHeaderText().toUpperCase();
        }

        @Override
        public void setHeaderText(String newHeaderText) {
            at.setHeaderText(newHeaderText);
        }
    }

    public interface ATableInterface {
        void setModel(List rows);

        String getHeaderText();

        void setHeaderText(String newHeaderText);
    }

    public static void main(String[] args) {
    }
}
