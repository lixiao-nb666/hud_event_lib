package com.nrmyw.hud_data_event_lib.manager.image;

public class HudImageManeger {

    private static HudImageManeger hudImageManeger;
    public  Listen listen;

    private boolean imageCanShow;

    private int hideNumb;
    private HudImageManeger(){

    }

    public static HudImageManeger getInstance(){
        if(null==hudImageManeger){
            synchronized (HudImageManeger.class){
                if(null==hudImageManeger){
                    hudImageManeger=new HudImageManeger();
                }
            }
        }
        return hudImageManeger;
    }

    public void setListen(Listen listen){
        this.listen=listen;
    }

    public void setImageCanShow(boolean imageCanShow){
        this.imageCanShow=imageCanShow;
        if(null==listen){
            return;
        }
        if(this.imageCanShow){
            hideNumb=0;
            listen.nowCanShowImage();
        }else {
            hideNumb=3;
            listen.nowMustSetImageHide();
        }
    }

    public boolean isImageCanShow(){
        return imageCanShow;
    }

    public int getHideNumb(){
        if(hideNumb>0){
            hideNumb--;
        }
        return hideNumb;
    }


    public interface Listen{

        public void nowCanShowImage();
        public void nowMustSetImageHide();

    }
}
