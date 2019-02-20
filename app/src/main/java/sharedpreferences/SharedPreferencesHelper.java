package sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by Administrator on 2019/2/12.
 */

public class SharedPreferencesHelper {

    private static volatile SharedPreferencesHelper preferencesHelper = null;

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void put(String key , Object value){
        if (!key.isEmpty() && value!=null){
            if (value instanceof String){
                editor.putString(key,(String) value);
            }
            else if (value instanceof Integer){
                editor.putInt(key,(Integer)value);
            }
            else if (value instanceof Boolean){
                editor.putBoolean(key,(Boolean)value);
            }
            else if (value instanceof Float){
                editor.putFloat(key,(Float)value);
            }
            else if (value instanceof Long){
                editor.putLong(key,(Long)value);
            }
            else {
                editor.putString(key,value.toString());
            }
            editor.commit();
        }

    }
    public Object get(String key , Object defultValue){

        if (!key.isEmpty() && defultValue!=null){
            if (defultValue instanceof String){
                return sharedPreferences.getString(key,(String) defultValue);
            }
            else if (defultValue instanceof Integer){
                return sharedPreferences.getInt(key,(Integer) defultValue);
            }
            else if (defultValue instanceof Boolean){
                return sharedPreferences.getBoolean(key,(Boolean) defultValue);
            }
            else if (defultValue instanceof Float){
                return sharedPreferences.getFloat(key,(Float) defultValue);
            }
            else if (defultValue instanceof Long){
                return sharedPreferences.getLong(key,(Long) defultValue);
            }
            else {
                return sharedPreferences.getString(key,defultValue.toString());
            }
        }

        return sharedPreferences.getString(key,defultValue.toString());
    }

    public void remove(String key){
        if (!key.isEmpty()){
            editor.remove(key);
            editor.commit();
        }
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }

    public Map<String,?> getAll(){
        return sharedPreferences.getAll();
    }


    public static SharedPreferencesHelper getInstance(Context context){
        if (preferencesHelper == null){
            synchronized (SharedPreferencesHelper.class){
                if (preferencesHelper == null){
                    preferencesHelper = new SharedPreferencesHelper(context);
                }
            }
        }
        return preferencesHelper;
    }

}
