package com.appzone.mylibrarys;

public class QurekaModel {

        Integer Image;
        String Title,Dis;

        public QurekaModel(Integer image, String title, String dis) {
                Image = image;
                Title = title;
                Dis = dis;
        }


        public Integer getImage() {
                return Image;
        }

        public void setImage(Integer image) {
                Image = image;
        }

        public String getTitle() {
                return Title;
        }

        public void setTitle(String title) {
                Title = title;
        }

        public String getDis() {
                return Dis;
        }

        public void setDis(String dis) {
                Dis = dis;
        }
}
