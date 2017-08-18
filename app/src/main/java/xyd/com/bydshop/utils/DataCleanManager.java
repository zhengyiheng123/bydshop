package xyd.com.bydshop.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/18
 * @time: 16:39
 * @description:
 */

public class DataCleanManager {

    /**
     * * 清除本应用所有的数据 * *
     * @param context
     */
    public static void cleanApplicationData(Context context) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanFiles(context);
    }


    /**
     * * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * *
     *
     * @param context
     */
    public static void cleanInternalCache(Context context) {
        clearCacheFolder(context.getCacheDir());
    }

    /**
     * * 清除/data/data/com.xxx.xxx/files下的内容 * *
     *
     * @param context
     */
    public static void cleanFiles(Context context) {
        clearCacheFolder(context.getFilesDir());
    }


    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     *
     * @param context
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            clearCacheFolder(context.getExternalCacheDir());
        }
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) *
     *
     * @param context
     */
    public static void cleanSharedPreference(Context context) {
        clearCacheFolder(new File("/data/data/"
                + context.getPackageName() + "/shared_prefs"));
    }


    /**
     * 删除方法
     * @param dir 目录
     * @return
     */
    private static int clearCacheFolder(File dir) {
        int deletedFiles = 0;
        if (dir!= null && dir.isDirectory()) {
            try {
                for (File child:dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child);
                    }
                    if (child.delete()) {
                        deletedFiles++;
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    /**
     * 清除缓存目录
     * @param dir 目录
     * @param curTime 当前系统时间
     * @return
     */
    private static int clearCacheFolder1(File dir, long curTime) {
        int deletedFiles = 0;
        if (dir!= null && dir.isDirectory()) {
            try {
                for (File child:dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder1(child, curTime);
                    }
                    if (child.lastModified() < curTime) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }


    /**
     * 计算缓存大小
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long fileSize = 0;
        String cacheSize = "0KB";
        fileSize += getFolderSize(context.getFilesDir());// /data/data/package_name/files
        fileSize += getFolderSize(context.getCacheDir()); // /data/data/package_name/cache
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            fileSize += getFolderSize(context.getExternalCacheDir());
        }
        if (fileSize > 0) {
            cacheSize = formatFileSize(fileSize);
        }
        return cacheSize;
    }


    /**
     * 获取目录文件大小
     * @param file
     * @return
     * @throws Exception
     */
    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        if (file == null) {
            return 0;
        }
        if (!file.isDirectory()) {
            return 0;
        }
        long size = 0;
        try {
            File[] fileList = file.listFiles(); //获取当前文件夹下的所有文件和文件夹
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件（path表示的是一个目录）
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

}
