package com.chivox;

import android.content.Context;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AiUtil {

    /**
     * 计算字符串的SHA1值
     */
    public static String sha1(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(message.getBytes(), 0, message.length());
            return bytes2hex(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算文件的MD5值
     */
    public static String md5(Context c, String fileName) {
        int bytes;
        byte buf[] = new byte[BUFFER_SIZE];
        try {
            InputStream is = c.getAssets().open(fileName);
            MessageDigest md = MessageDigest.getInstance("MD5");
            while ((bytes = is.read(buf, 0, BUFFER_SIZE)) > 0) {
                md.update(buf, 0, bytes);
            }
            is.close();
            return bytes2hex(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算英文句子中，单词的个数
     */
    public static long getWordCount(String sent) {
        return sent.trim().split("\\W+").length;
    }

    /**
     * 计算中文句子中，汉字的个数
     */
    public static long getHanziCount(String pin1yin1) {
        return pin1yin1.trim().split("-").length;
    }

    /**
     * 获取应用缓存路径
     */
    public static File externalFilesDir(Context c) {
        File f = c.getExternalFilesDir(null);
        // not support android 2.1
        if (f == null || !f.exists()) {
            f = c.getFilesDir();
        }
        return f;
    }

    /**
     * 读取文件内容
     */
//    public static String readFile(File file) {
//        String res = null;
//        try {
//            FileInputStream fin = new FileInputStream(file);
//            int length = fin.available();
//            byte[] buffer = new byte[length];
//            fin.read(buffer);
//            res = EncodingUtils.getString(buffer, "UTF-8");
//            fin.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return res;
//    }

    /**
     * 从Assets 读取文件内容
     */
//    public static String readFileFromAssets(Context c, String fileName) {
//        String res = null;
//        try {
//            InputStream in = c.getAssets().open(fileName);
//            int length = in.available();
//            byte[] buffer = new byte[length];
//            in.read(buffer);
//            res = EncodingUtils.getString(buffer, "UTF-8");
//            in.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return res;
//    }

    /**
     * 将字符串写入指定目录文件
     */
    public static void writeToFile(String filePath, String string) {
        try {
            FileOutputStream fout = new FileOutputStream(filePath);
            byte[] bytes = string.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将字符串写入指定文件
     */
    public static void writeToFile(File f, String string) {
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(string);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将InputStream 写入指定文件
     */
    public static void writeToFile(File f, InputStream is) {
        int bytes;
        byte[] buf = new byte[BUFFER_SIZE];
        try {
            FileOutputStream fos = new FileOutputStream(f);
            while ((bytes = is.read(buf, 0, BUFFER_SIZE)) > 0) {
                fos.write(buf, 0, bytes);
            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从Assets 解压zip文件
     *
     * @return 解压后的目录
     */
//    public static File unzipFile(Context c, String fileName) {
//        try {
//            String pureName = fileName.replaceAll("\\.[^.]*$", "");
//
//            File filesDir = externalFilesDir(c);
//            File targetDir = new File(filesDir, pureName);
//
//            String md5sum = md5(c, fileName);
//            File md5sumFile = new File(targetDir, ".md5sum");
//
//            if (targetDir.isDirectory()) {
//                if (md5sumFile.isFile()) {
//                    String md5sum2 = readFile(md5sumFile);
//                    if (md5sum2.equals(md5sum)) {// already extracted
//                        return targetDir;
//                    }
//                }
//                // remove old dirty resource
//                removeDirectory(targetDir);
//            }
//
//            unzip(c, fileName, targetDir);
//            writeToFile(md5sumFile, md5sum);
//            return targetDir;
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e(tag, "Failed to extract resource", e);
//        }
//        return null;
//    }

    private static String tag = "AiUtil";
    private static int BUFFER_SIZE = 8192;

    private static String bytes2hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    private static void removeDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    removeDirectory(files[i]);
                }
                files[i].delete();
            }
            directory.delete();
        }
    }

    public static File getFilesDir(Context context) {
        File targetDir = context.getExternalFilesDir(null);
        if (targetDir == null || !targetDir.exists()) {
            targetDir = context.getFilesDir();
        }
        return targetDir;
    }

    private static void unzip(Context c, String fileName, File targetDir)
            throws IOException {
        InputStream is = c.getAssets().open(fileName);
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is,
                BUFFER_SIZE));

        ZipEntry ze;
        while ((ze = zis.getNextEntry()) != null) {
            if (ze.isDirectory()) {
                new File(targetDir, ze.getName()).mkdirs();
            } else {
                File file = new File(targetDir, ze.getName());
                File parentdir = file.getParentFile();
                if (parentdir != null && (!parentdir.exists())) {
                    parentdir.mkdirs();
                }
                int pos;
                byte[] buf = new byte[BUFFER_SIZE];
                OutputStream bos = new FileOutputStream(file);
                while ((pos = zis.read(buf, 0, BUFFER_SIZE)) > 0) {
                    bos.write(buf, 0, pos);
                }
                bos.flush();
                bos.close();
            }
        }
        zis.close();
        is.close();
    }


//    public void testHanziToPinyin() {
//        ArrayList<HanziTo.Token> list = HanziToPinyin.getInstance().get("单赵 钱 孙 李 周 吴 郑 王冯 陈 褚 卫 蒋 沈 韩 杨 朱");
//        for (MediaSessionCompat.Token token : list) {
//            System.out.print(token.source + " , " + token.target + " , " + token.type);
//            System.out.println();
//        }
//
//        String str = "单赵钱孙李周吴郑王冯陈褚卫abc";
//        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
//        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
//        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
//        for(int i = 0 ; i < str.length() ; i++)
//        {
//            char c = str.charAt(i);
//
//            String[] vals = PinyinHelper.toHanyuPinyinStringArray(c, format);
//            System.out.print(Arrays.toString(vals));
//        }
//    }

    /**
     * 汉字转换拼音，字母原样返回，都转换为小写
     *
     * @param input
     * @return
     */
//    public static String getPinYin(String input) {
//        ArrayList<HanziToPinyin.Token> list = HanziToPinyin.getInstance().get(input);
//        StringBuffer sbf = new StringBuffer();
//        for (HanziToPinyin.Token token : list) {
//            sbf.append(token.target + token.type + "\b");
//            System.out.print(token.source + " , " + token.target + " , " + token.type);
//            System.out.println();
//        }
//
////        String str = "单赵钱孙李周吴郑王冯陈褚卫abc";
//        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
//        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
//        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
//        for (int i = 0; i < input.length(); i++) {
//            try {
//                char c = input.charAt(i);
//                String[] vals = PinyinHelper.toHanyuPinyinStringArray(c, format);
//
//                sbf.append(vals);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        sbf.toString();
//        return sbf.toString().toLowerCase();
//    }

    //汉字返回拼音，字母原样返回，都转换为小写(默认取得的拼音全大写)
    public static String getPinYin(String input) {
//        ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(input);
//        StringBuilder sb = new StringBuilder();
//        if (tokens != null && tokens.size() > 0) {
//            for (HanziToPinyin.Token token : tokens) {
//                if (HanziToPinyin.Token.PINYIN == token.type) {
//                    sb.append(token.target);
//                } else {
//                    sb.append(token.source);
//                }
//            }
//        }
//        return sb.toString().toLowerCase();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            result.append(getCharacterPinYin(input.charAt(i))[0] + " ");
        }

        return result.toString();
    }


    //将汉字转换成拼音--转换单个字符
    public static String[] getCharacterPinYin(char c) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);//1-4数字表示英标 (zhong4)
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String pinyin[] = null;
        try {
            pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
            //注意返回的字符串数组，因为可能是多音字
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        // 如果c不是汉字，toHanyuPinyinStringArray会返回null
        if (pinyin == null) {
            return new String[]{""};
//            return null;
        } else {
            return pinyin;
        }
    }


}
