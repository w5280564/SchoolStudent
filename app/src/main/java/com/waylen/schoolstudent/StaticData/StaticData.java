package com.waylen.schoolstudent.StaticData;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StaticData {
    public static void buttonScale(Button bt, float w, float h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = bt.getLayoutParams();
        lp.height = (int) (h * scale);
        lp.width = (int) (w * scale);
        bt.setLayoutParams(lp);
    }

//	public static void GestureImageViewScale(GestureImageView img, float w,
//			float h) {
//		LayoutParams lp = null;
//		lp = img.getLayoutParams();
//		lp.height = (int) (h * scale);
//		lp.width = (int) (w * scale);
//		img.setLayoutParams(lp);
//	}

    public static void imageViewScale(ImageView img, float w, float h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = img.getLayoutParams();
        lp.height = (int) (h * scale);
        lp.width = (int) (w * scale);
        img.setLayoutParams(lp);
    }

    public static void textViewScale(TextView tx, float w, float h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = tx.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        tx.setLayoutParams(lp);
    }

    public static void editTextScale(EditText et, float w, float h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = et.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        et.setLayoutParams(lp);
    }

    public static void linearLayoutScale(LinearLayout ly, float w, float h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = ly.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        ly.setLayoutParams(lp);
    }

    public static void relativeLayoutScale(RelativeLayout rel, float w, float h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = rel.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        rel.setLayoutParams(lp);
    }

    public static void scrollViewScale(ScrollView sc, float w, float h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = sc.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        sc.setLayoutParams(lp);
    }

    public static void listViewScale(ListView lv, float w, float h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = lv.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        lv.setLayoutParams(lp);
    }

//    public static void viewpaperscarle(ViewPager vp, int w, int h) {
//        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
//        LayoutParams lp = vp.getLayoutParams();
//        if (h != 0) {
//            lp.height = (int) (h * scale);
//        }
//        if (w != 0) {
//            lp.width = (int) (w * scale);
//        }
//        vp.setLayoutParams(lp);
//    }

    public static void layoutParamsScale(LayoutParams itemParams, int w, int h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        if (h != 0) {
            itemParams.height = (int) (h * scale);
        }
        if (w != 0) {
            itemParams.width = (int) (w * scale);
        }
    }

    public static void radioGroupScale(RadioGroup tm, int w, int h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = tm.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        tm.setLayoutParams(lp);
    }

    public static void radioButtonScale(RadioButton rb, float w, float h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = rb.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        rb.setLayoutParams(lp);
    }

    public static void horizontalScrollViewScale(HorizontalScrollView hs, int w, int h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = hs.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        hs.setLayoutParams(lp);
    }

    public static void checkBoxScale(CheckBox cb, int w, int h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = cb.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        cb.setLayoutParams(lp);
    }

    public static void ProgressBarScale(ProgressBar pbar, int w, int h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = pbar.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        pbar.setLayoutParams(lp);
    }

    public static void gridViewScale(GridView grv, int w, int h) {
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = grv.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        grv.setLayoutParams(lp);
    }

    public static  void ViewScale(View v,int w,int h){
        float scale = Float.parseFloat(UrlVO.getShareData("scale", MyApplication.mycontext));
        LayoutParams lp = null;
        lp = v.getLayoutParams();
        if (h != 0) {
            lp.height = (int) (h * scale);
        }
        if (w != 0) {
            lp.width = (int) (w * scale);
        }
        v.setLayoutParams(lp);
    }



    /**
     * 验证邮箱格式
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String pattern_email = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        if (!email.matches(pattern_email)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isPhone(String phone) {
        /*截止到2015年
         * 移动：134、135、136、137、138、139、150、151、152、157(TD)、158、159、182、183、184、187、188、147、188
		 * 联通：130、131、132、152、155、156、185、186、145、176 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必须为3、4、5、7、8可改为 [0-9]，其他位置的可以为0-9 
		 */
        String telRegex = "[1][34578]\\d{9}";// "[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phone))
            return false;
        else
            return phone.matches(telRegex);
    }

    /**
     * 验证昵称
     *
     * @param context
     * @param str
     * @return
     */
    @SuppressLint("ShowToast")
    public static boolean verifyNickname(Context context, String str) {
        String nickname = str;
        if (nickname == null || nickname.length() == 0) {
            Toast.makeText(context, "不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        int len = 0;
        char[] nickchar = nickname.toCharArray();
        for (int i = 0; i < nickchar.length; i++) {
            if (isChinese(nickchar[i])) {
                len += 2;
            } else {
                len += 1;
            }
        }
        if (len < 4 || len > 15) {
            Toast.makeText(context, "正确的昵称应该为\n1、4-15个字符\n2、2-7个汉字\n3、不能是邮箱和手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //判断是中文
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 对外提供getSHA(String str)方法 SHA-1加密
     *
     * @author randyjia
     */
    @SuppressLint("DefaultLocale")
    public static class SHA {
        // SHA1 加密实例
        public static String encryptToSHA(String info) {
            byte[] digesta = null;
            try {
                // 得到一个SHA-1的消息摘要
                MessageDigest alga = MessageDigest.getInstance("SHA-1");
                // 添加要进行计算摘要的信息
                alga.update(info.getBytes());
                // 得到该摘要
                digesta = alga.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            // 将摘要转为字符串
            String rs = byte2hex(digesta);
            return rs.toUpperCase();
        }

        public static String byte2hex(byte[] b) {
            String hs = "";
            String stmp = "";
            for (int n = 0; n < b.length; n++) {
                stmp = (Integer.toHexString(b[n] & 0XFF));
                if (stmp.length() == 1) {
                    hs = hs + "0" + stmp;
                } else {
                    hs = hs + stmp;
                }
            }
            return hs;
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * String转意URL格式
     *
     * @param paramString
     * @return
     */
    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            return "";
        }
        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
        }

        return "";
    }

    /**
     * 保存图片 每个项目要修改存入文件名
     *
     * @param
     * @param
     * @param
     * @throws IOException
     */
    @SuppressLint("ShowToast")
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Cpp");
//		File appDir = new File(context.getFilesDir().getAbsoluteFile(), "Cpp");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        if (bmp != null) {
            try {
                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(),
                        fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + file.getAbsolutePath())));
    }

    /**
     * 将字符串转成二维码图片
     *
     * @param str
     * @return
     * @throws WriterException
     */
//	public static Bitmap Create2DCode(String str) {
//		// 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
//		BitMatrix matrix = null;
//		try {
//			matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE,
//					(int) (500 * scale), (int) (500 * scale));
//		} catch (WriterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		int width = matrix.getWidth();
//		int height = matrix.getHeight();
//		// 二维矩阵转为一维像素数组,也就是一直横着排了
//		int[] pixels = new int[width * height];
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//				if (matrix.get(x, y)) {
//					pixels[y * width + x] = 0xff000000;// 黑色
//				} else {
//					pixels[y * width + x] = 0xffffffff;// 白色
//				}
//			}
//		}
//		Bitmap bitmap = Bitmap.createBitmap(width, height,
//				Config.ARGB_8888);
//		// 通过像素数组生成bitmap,具体参考api
//		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//		return bitmap;
//	}

    /**
     * 截取数字
     *
     * @param content
     * @return
     */
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     * 截取非数字
     *
     * @param content
     * @return String汉字
     */
    public static String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     * 得到本地路径名
     *
     * @param filename
     * @param mBitmap
     * @return
     */
    public static String createDir(String filename, Bitmap mBitmap) {
        File sdcardDir = Environment.getExternalStorageDirectory();
        // 得到一个路径，内容是sdcard的文件夹路径和名字
        String path = sdcardDir.getPath() + "/Cpp";
        File path1 = new File(path);
        if (!path1.exists())
            // 若不存在，创建目录，可以在应用启动的时候创建
            path1.mkdirs();
        path = path + "/" + filename;
        File picf = new File(path);
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(picf);
            mBitmap.compress(CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "null";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "null";
        }
    }

    /**
     * 加载本地图片 http://bbs.3gstdy.com
     *
     * @param url
     * @return 返回Bitmap
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 截取图片宽不变高一半
     *
     * @param bit
     * @return
     */
    public static Bitmap removeBit(Bitmap bit) {
        Bitmap picNewRes = null;
        // 获取需要的图片的宽高
        int picWidth = bit.getWidth();
        int picHeight = bit.getHeight() / 2;
        // 从源图片中截取需要的部分
        picNewRes = Bitmap.createBitmap(bit, 0, picHeight / 2, picWidth, picHeight);
        return picNewRes;
    }

    /**
     * 截取图片
     *
     * @param bitmap     原图
     * @param edgeLength 希望得到的正方形部分的边长
     * @return 缩放截取正中部分后的位图。
     */
    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
        if (null == bitmap || edgeLength <= 0) {
            return null;
        }
        Bitmap result = null;
        result = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();

        if (widthOrg > edgeLength && heightOrg > edgeLength) {
            // 压缩到一个最小长度是edgeLength的bitmap
            int longerEdge = (int) (edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg));

            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
            int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
            Bitmap scaledBitmap;
            try {
                //src:源图的地址 dstWidth：新图片的宽dstHeight：新图片的高filter：是否过滤
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            } catch (Exception e) {
                return null;
            }
            // 从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaledHeight - edgeLength) / 2;
            try {
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
                scaledBitmap.recycle();
                scaledBitmap = null;
            } catch (Exception e) {
                return null;
            }
        }

        return result;
    }

    /**
     * 判断是否有网true有网
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 是否输入的是空格
     *
     * @param
     */
    public static boolean Stringspace(String space) {
        if (space.matches("\\s*")) // "/s"匹配任意的空白符，包括空格，制表符(Tab)，换行符，中文全角空格
            return true;
        else
            return false;
    }


    /**
     * 验证URL正则
     * @param httpstr
     * @return
     */
    public static boolean StringHttp(String httpstr){
//		Pattern pattern=Pattern.compile("(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*");
        Pattern pattern=Pattern.compile("^((http|https)://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$");
        Matcher matcher=pattern.matcher(httpstr);
        if(matcher.find())
            return true;//是网站
        else
            return false;
    }



    /**
     * ArrayList排序
     *
     * @param baseArr
     * @param comtxt
     * @return
     */
    public static ArrayList<HashMap<String, Object>> ComparatorArr(ArrayList<HashMap<String, Object>> baseArr, final String comtxt) {
        Collections.sort(baseArr, new Comparator<HashMap<String, Object>>() {
            @Override
            public int compare(HashMap<String, Object> arg0, HashMap<String, Object> arg1) {
                return (arg1.get(comtxt).toString()).compareTo(arg0.get(comtxt).toString());
            }
        });

        return baseArr;
    }

    /**
     * List按Date时间排序
     *
     * @param baseArr
     * @param str
     * @return
     */
    public static List<HashMap<String, Object>> timeArr(List<HashMap<String, Object>> baseArr, final String str) {
        Collections.sort(baseArr, new Comparator<HashMap<String, Object>>() {
            @Override
            public int compare(HashMap<String, Object> arg0, HashMap<String, Object> arg1) {
                Date a = null;
                Date b = null;
                String retime = "";
                try {
                    String time1 = arg0.get(str).toString().replace("T", " ");
                    String time2 = arg1.get(str).toString().replace("T", " ");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                    a = sdf.parse(time1);
                    b = sdf.parse(time2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return b.compareTo(a);
            }
        });
        return baseArr;
    }


    /**
     * 格式20151023141514为2015.10.23 HH:mm
     *
     * @param datestr
     * @return 2015-10-23T14:15:14
     */
    public static String Datestyle(String datestr) {
        String dateString = null;
        if (datestr != null) {
            StringBuffer sbf = new StringBuffer(datestr);
            sbf.insert(4, ".").insert(7, ".").insert(10, " ").insert(13, ":");
            dateString = sbf.toString();
        }
        return dateString;
    }


    //截取16位
    public static String Datatype(String datestr) {
        String datestring;
        if (datestr.equals("null") || TextUtils.isEmpty(datestr)) {
            datestring = "";
        } else {
            datestring = datestr.substring(0, 16).replace("-", ".").replace("T", " ");
        }
        return datestring;
    }

    //截取10位
    public static String Datatypetwo(String datestr) {
        String datestring = datestr.substring(0, 10).replace("-", ".");
        return datestring;
    }

    //历史截取10位
    public static String DateHis(String datestr) {
        String datestring = datestr.substring(0, 10).replace("/", ".");
        return datestring;
    }


    //是否是空格 true是空
    public static boolean isSpace(String str) {
        boolean space;
        if (str == null || TextUtils.isEmpty(str)) {
            space = true;
        } else {
            space = false;
        }
        return space;
    }

    public static String idstring(ArrayList<String> arrstr) {
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < arrstr.size(); i++) {
            sbf.append(arrstr.get(i) + ",");
        }
        return sbf.toString();
    }




    /**
     * 获取软件Name版本号
     *
     * @param context
     * @return
     */
    public static String getversionName(Context context) {
        String verName = "";
        try {
            //注意："com.example.try_downloadfile_progress"对应AndroidManifest.xml里的package="……"部分
            verName = context.getPackageManager().getPackageInfo("com.waylen.zenzox", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("msg", e.getMessage());
        }
        return verName;
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    // 根据亮度值修改当前window亮度
     public static void changeAppBrightness(Context context, int brightness) {
             Window window = ((Activity) context).getWindow();
             WindowManager.LayoutParams lp = window.getAttributes();
             if (brightness == -1) {
                     lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
                 } else {
                     lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
                 }
             window.setAttributes(lp);
         }



}