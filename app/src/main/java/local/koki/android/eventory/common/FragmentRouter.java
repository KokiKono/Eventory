package local.koki.android.eventory.common;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;

import local.koki.android.eventory.model.EventManager;

/**
 * MVCモデル移行にともない各Fragmentの切替を行うクラス。
 * Created by 浩生 on 2017/02/10.
 */

public class FragmentRouter {
    public static final String ARGS_KEY="fragment_key";
    public enum Tag{
        //検索は使用されていないため使用しない。
        //Search("探す", EventManager.CheckStatus.Search),
        NoKeep("興味なし", EventManager.CheckStatus.NoKeep),
        New("新着情報",EventManager.CheckStatus.NoCheck),
        Keep("興味あり",EventManager.CheckStatus.Keep),
        Configuration("設定", EventManager.CheckStatus.None);
        public String tabValue;
        public EventManager.CheckStatus checkStatus;
        private Tag(String tagValue, EventManager.CheckStatus checkStatus){
            this.tabValue=tagValue;
            this.checkStatus=checkStatus;
        }
        public String getTabTitle(){
            return this.tabValue;
        }
        public static Tag get(int position){
            return Tag.get(position);
        }
    }
    private static Map<Tag,Class> showcase=new HashMap<>();

    private FragmentRouter(){}

    public static void register(Tag tag,Class fragmentClass){
        showcase.put(tag,fragmentClass);
    }
    private static Class get(Tag tag){
        return showcase.get(tag);
    }

    /**
     * Tagに紐づけられたフラグメントを呼び出す。
     * FragmentにTagの登録を行うのはApplicationクラスを
     * 継承したCommonクラスで定義する。
     * @param fragmentManager
     * @param container
     * @param tag
     * @param args
     * @param addToBaskStack
     */
    public static void replace(FragmentManager fragmentManager, int container, Tag tag, Bundle args,boolean addToBaskStack){

        Fragment fragment=fragmentManager.findFragmentByTag(String.valueOf(tag));
        if(fragment==null){
            try{
                Class fragmentClass=get(tag);
                fragment=(Fragment)fragmentClass.newInstance();
            }catch (Exception e){
                return;
            }
        }
        fragment.setArguments(args);

        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(container,fragment,String.valueOf(tag));
        if(addToBaskStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public static Fragment newInstance(Tag tag,Bundle args){
        try{
            Class fragmentClass = get(tag);
            Fragment fragment=(Fragment)fragmentClass.newInstance();
            fragment.setArguments(args);
            return fragment;
        }catch (Exception e){
            return  null;
        }
    }
    public static Bundle newFragmentArgs(Tag tag){
        Bundle bundle=new Bundle();
        bundle.putInt(ARGS_KEY,tag.checkStatus.getCode());
        return bundle;
    }
}
