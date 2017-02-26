package local.koki.android.eventory.common;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import local.koki.android.eventory.model.EventManager;
import local.koki.android.eventory.viewController.ConfigurationFragment;
import local.koki.android.eventory.viewController.EventFragment;
import local.koki.android.eventory.viewController.SearchFragment;

/**
 * Created by 浩生 on 2017/02/10.
 */

public class FragmentRouter {
    public static final String ARGS_KEY="fragment_key";
    public enum Tag{
        //Search("探す", EventManager.CheckStatus.Search, SearchFragment.class),
        New("新着情報",EventManager.CheckStatus.NoCheck, EventFragment.class),
        NoKeep("興味なし", EventManager.CheckStatus.NoKeep,EventFragment.class),
        Keep("興味あり",EventManager.CheckStatus.Keep,EventFragment.class),
        Configuration("設定", EventManager.CheckStatus.None, ConfigurationFragment.class);
        public String tabValue;
        public EventManager.CheckStatus checkStatus;
        private Tag(String tagValue, EventManager.CheckStatus checkStatus,Class fragmentClass){
            this.tabValue=tagValue;
            this.checkStatus=checkStatus;
            register(this,fragmentClass);
        }
        public String getTabTitle(){
            return this.tabValue;
        }
        public static Tag get(int position){
            return Tag.get(position);
        }
        public int getAt(){
            return indexOf(this);
        }
        public static int indexOf(Tag tag){
            int count=0;
            for(Tag t:Tag.values()){
                if(t==tag){
                    return count;
                }
                count++;
            }
            return -1;
        }
        public static String getTitle(Tag tag){
            if(showTitle.containsKey(tag)==false) {
                return tag.tabValue;
            }
            return showTitle.get(tag);
        }
        public void setTitle(String title){
            showTitle.replace(this,title);
        }
        public void setTitle(int resorceId){
            setTitle(MainApplication.Companion.getString(resorceId));
        }
    }
    private static Map<Tag,Class> showcase=new HashMap<>();
    private static Map<Tag,Integer> showOrder =new HashMap<>();
    private static Map<Tag,String> showTitle=new HashMap<>();

    private FragmentRouter(){}

    public static void register(Tag tag,Class fragmentClass){
        showcase.put(tag,fragmentClass);
        showOrder.put(tag,showcase.size()+1);
        showTitle.put(tag,"");
    }
    public static void register(Tag tag,Class fragmentClass,int resorceId){
        showcase.put(tag,fragmentClass);
        showOrder.put(tag,showcase.size()+1);
        showTitle.put(tag,MainApplication.Companion.getString(resorceId));
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

    public static Fragment newFragmentInstance(Tag tag, Bundle args){
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
