package com.example.imxbkslibrary.util;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chc on 2017/6/8/008.
 * 将图片先按照比例缩放，然后再进行质量压缩
 *
 *
 */
public class IMBitmapComPressUtils {
    private Context context;

    public IMBitmapComPressUtils(){

    }

    /**
     * 图片按比例大小压缩(用于二维码)
     *
     * @param srcPath
     * @return
     */
    public static Bitmap getDecordeImage(Context context, String srcPath, float phoneHeight, float phoneWinth)
    {

        if (srcPath == null)
        {
            return null;
        }
        File file = new File(srcPath);
        if (!file.exists())
        {
            return null;
        }
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //横屏拍的
        if((w/h)>1){
            int temp=w;
            w=h;
            h=temp;
        }
        // 设置手机的宽高
        float hh = phoneHeight;// 这里设置高度
        float ww = phoneWinth;// 这里设置宽度
//        float hh = 800f;// 这里设置高度
//        float ww = 480f;// 这里设置宽度
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww)
        {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh)
        {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

//        if(Build.VERSION.SDK_INT>=24){
//            try {
//                bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(Uri.parse(srcPath)));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }else{
//            bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
//        }

        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }

    public static  Bitmap compressImage(Bitmap image) {
        if(image==null){
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>700) {  //循环判断如果压缩后图片是 否大于700k,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    //保存图片，返回路径
    public static String  saveBitmap(Context context,Bitmap bitmap) {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String origFileName = "osc_" + timeStamp + ".jpg";
        File f = new File(IMCacheUtils.getCacheDirectory(context, true, "icon") + origFileName);

        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            return f.getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    /*******************android 7.0 URi崩溃，获取uri  api 19以上***************************/

    @TargetApi(19)
    public static String getImagePathHeight(Uri data, Context context){
        if(data==null){
            return null;
        }
        Uri imageUri=null;
        String imagePath = null;
        imageUri = data;
        if (DocumentsContract.isDocumentUri(context, imageUri)) {
            //如果是document类型的uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(imageUri);
            if ("com.android.providers.media.documents".equals(imageUri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getRealImagePaht(context,MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.downloads.documents".equals(imageUri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getRealImagePaht(context,contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            //如果是content类型的Uri，则使用普通方式处理
            imagePath = getRealImagePaht(context,imageUri, null);
        } else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            //如果是file类型的Uri,直接获取图片路径即可
            imagePath = imageUri.getPath();
        }
        return imagePath;
    }



    public static String getRealImagePaht(Context context,Uri uri, String selection) {
        String path = null;
        //通过Uri和selection老获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (!cursor.isAfterLast()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return path;
    }


    public static String getRealFilePath( final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
