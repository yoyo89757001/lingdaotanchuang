package com.example.xiaojun.lingdaotanchuang;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.example.xiaojun.lingdaotanchuang.beans.DaoMaster;
import com.example.xiaojun.lingdaotanchuang.beans.DaoSession;
import com.example.xiaojun.lingdaotanchuang.cookies.CookiesManager;
import com.tencent.smtt.sdk.QbSdk;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tangjun on 14-8-24.
 */
public class MyApplication extends Application {
	public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
	private final static String TAG = "CookiesManager";
	public static MyApplication myApplication;
	public static OkHttpClient okHttpClient=null;
	private DaoMaster.DevOpenHelper mHelper;
	private SQLiteDatabase db;
	public DaoMaster mDaoMaster;
	public DaoSession mDaoSession;
	// 超时时间
	public static final int TIMEOUT = 1000 * 60;
	public static String zhuji_string=null,shiping_string=null;

	@Override
	public void onCreate() {
		super.onCreate();
		JPushInterface.init(getApplicationContext());

		setDatabase();
		myApplication = this;
//搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

		QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

			@Override
			public void onViewInitFinished(boolean arg0) {
				// TODO Auto-generated method stub
				//x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
				Log.d("app", " onViewInitFinished is " + arg0);
			}

			@Override
			public void onCoreInitFinished() {
				// TODO Auto-generated method stub
			}
		};
		//x5内核初始化接口
		QbSdk.initX5Environment(getApplicationContext(),  cb);

		String path= Environment.getExternalStorageDirectory()+File.separator+"linhefile";
		File destDir = new File(path);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}

		try {
			Reservoir.init(this, 1024*1024); //in bytes 1M
		} catch (Exception e) {
			//failure
		}

		getOkHttpClient();


	//	JPushInterface.init(this);

//		try {
//			File cacheDir = getDiskCacheDir("bitmap");
//			if (!cacheDir.exists()) {
//				cacheDir.mkdirs();
//			}
//			mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(), 1, 8 * 1024 * 1024);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//保存当前时间
		//Log.d(TAG, DateUtils.timedate(System.currentTimeMillis() + ""));
//		Type resultType3 = new TypeToken<String>() {}.getType();
//		try {
//
//			String s = Reservoir.get("time", resultType3);
//		}catch (Exception e){
//
//			try {
//				String s=System.currentTimeMillis()+"";
//
//				Reservoir.put("time", DateUtils.timedate(s.substring(0,s.length()-3)));
//				Log.d(TAG,DateUtils.timedate(s.substring(0,s.length()-3)) + "");
//			} catch (Exception ei) {
//				//failure;
//				Log.d(TAG, ei.getMessage());
//			}
//
//		}


	}



	public static MyApplication getAppContext() {
		return myApplication;
	}

	/**
	 * 设置greenDao
	 */
	private void setDatabase() {
		// 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
		// 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
		// 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
		// 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
		mHelper = new DaoMaster.DevOpenHelper(this, "notes-db22233", null);
		db = mHelper.getWritableDatabase();
		// 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
		mDaoMaster = new DaoMaster(db);
		mDaoSession = mDaoMaster.newSession();



	}


	public  DaoSession getDaoSession() {
		return mDaoSession;
	}
	public  SQLiteDatabase getDb() {
		return db;
	}


	public File getDiskCacheDir(String uniqueName) {
		String cachePath;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			cachePath = MyApplication.getAppContext().getExternalCacheDir().getPath();
		} else {
			cachePath = MyApplication.getAppContext().getCacheDir().getPath();
		}
		return new File(cachePath + File.separator + uniqueName);
	}

	public int getAppVersion() {
		try {
			PackageInfo info = MyApplication.getAppContext().getPackageManager().getPackageInfo(MyApplication.getAppContext().getPackageName(), 0);
			return info.versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return 1;
	}


	public static OkHttpClient getOkHttpClient(){
		if (okHttpClient==null){
			okHttpClient = new OkHttpClient.Builder()
					.writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
					.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
					.readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
					.cookieJar(new CookiesManager())
					.retryOnConnectionFailure(true)
					.build();

//			JSONObject json = new JSONObject();
//			try {
//				json.put("username", "test@megvii.com");
//				json.put("password", "123456");
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}


			//创建一个RequestBody(参数1：数据类型 参数2传递的json串)
		//	RequestBody requestBody = RequestBody.create(JSON, json.toString());

			RequestBody body = new FormBody.Builder()
					.add("username", "test@megvii.com")
					.add("password", "123456")
					.build();

			Request.Builder requestBuilder = new Request.Builder();
			requestBuilder.header("User-Agent", "Koala Admin");
			requestBuilder.header("Content-Type","application/json");
			requestBuilder.post(body);
			requestBuilder.url("http://192.166.2.65/auth/login");
			Request request = requestBuilder.build();

			Call mcall= okHttpClient.newCall(request);
			mcall.enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {

				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {

					Log.d(TAG, "123   "+response.body().string());
//					if (response.isSuccessful()){
//						Headers headers = response.headers();
//						List<String> cookies = headers.values("Set-Cookie");
//						//Log.d(TAG, "456   "+cookies.get(0).toString());
//						for (String str:cookies) {
//							if (str.startsWith("Koala Admin")) {
//								//将sessionId保存到本地
//
//							}
//						}
//					}

				}
			});
		}
		return okHttpClient;
	}
}
