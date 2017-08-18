package xyd.com.bydshop.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.customview.CircleImageView;
import xyd.com.bydshop.customview.CustomDialog;
import xyd.com.bydshop.entity.AvatarModel;
import xyd.com.bydshop.glide.GlideUtil;
import xyd.com.bydshop.runtimepermissions.PermissionUtils;
import xyd.com.bydshop.runtimepermissions.PermissionsManager;
import xyd.com.bydshop.serviceapi.MineApi;
import xyd.com.bydshop.utils.ActivityFactory;
import xyd.com.bydshop.utils.FileUtils;
import xyd.com.bydshop.utils.GetImagePath;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.ToastUtils;


/**
 * Created by ${zxl} on 2017/4/5.
 * Describe:  个人中心
 * CHange:
 */

public class WodeFragment extends Fragment {

    @Bind(R.id.wode_tv_rili)
    TextView wodeTvRili;
    @Bind(R.id.wode_tv_time)
    TextView wodeTvTime;
    @Bind(R.id.wode_tv_shangjia)
    TextView wodeTvShangjia;
    @Bind(R.id.wode_tv_tongzhi)
    TextView wodeTvTongzhi;
    @Bind(R.id.wode_tv_tiaojian)
    TextView wodeTvTiaojian;
    @Bind(R.id.wode_tv_fankui)
    TextView wodeTvFankui;
    @Bind(R.id.wode_tv_set)
    TextView wodeTvSet;
    @Bind(R.id.wode_iv_head)
    ImageView wodeIvHead;
    @Bind(R.id.wode_tv_name)
    TextView wodeTvName;
    @Bind(R.id.ll1)
    LinearLayout ll1;
    @Bind(R.id.ll2)
    LinearLayout ll2;
    @Bind(R.id.ll3)
    LinearLayout ll3;
    @Bind(R.id.ll4)
    LinearLayout ll4;
    private File outputFile;
    private File file;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wode, container, false);

        ButterKnife.bind(this, view);
        initView();
//        Drawable d=getResources().getDrawable(R.mipmap.tongzhi3);
//        wodeTvSet.setCompoundDrawablesWithIntrinsicBounds(d,null,null,null);
        return view;

    }

    private void initView() {
        if (PublicStaticData.isCanyin) {
            Drawable d = getResources().getDrawable(R.mipmap.yijian1);
            Drawable d1 = getResources().getDrawable(R.mipmap.jiantou2);
            wodeTvRili.setCompoundDrawablesWithIntrinsicBounds(null, d, null, null);
            wodeTvRili.setText("意见记录单");
            d = getResources().getDrawable(R.mipmap.rili1);
            wodeTvTime.setCompoundDrawablesWithIntrinsicBounds(null, d, null, null);
            wodeTvTime.setText("订单日历");
            d = getResources().getDrawable(R.mipmap.shijian);
            wodeTvTongzhi.setCompoundDrawablesWithIntrinsicBounds(d, null, d1, null);
            wodeTvTongzhi.setText("营业时间");
            wodeTvTiaojian.setText("取消条件");

        } else {
            Drawable d = getResources().getDrawable(R.mipmap.yijian1);
            Drawable d1 = getResources().getDrawable(R.mipmap.jiantou2);
            wodeTvRili.setCompoundDrawablesWithIntrinsicBounds(null, d, null, null);
            wodeTvRili.setText("意见记录单");
            d = getResources().getDrawable(R.mipmap.dingshishijian);
            wodeTvTime.setCompoundDrawablesWithIntrinsicBounds(null, d, null, null);
            wodeTvTime.setText("营业时间");
            d = getResources().getDrawable(R.mipmap.tongzhi3);
            wodeTvTongzhi.setCompoundDrawablesWithIntrinsicBounds(d, null, d1, null);
            wodeTvTongzhi.setText("官方通知");
            wodeTvTiaojian.setText("订票说明");
        }
        GlideUtil.getInstance().loadCircleImage(getActivity(), wodeIvHead, PublicStaticData.sharedPreferences.getString(PublicStaticData.AVATAR, ""));
        wodeTvName.setText(PublicStaticData.sharedPreferences.getString("name", ""));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.wode_tv_rili, R.id.wode_tv_time, R.id.wode_tv_shangjia, R.id.wode_tv_tongzhi, R.id.wode_tv_tiaojian, R.id.wode_tv_fankui, R.id.wode_tv_set,
            R.id.wode_iv_head, R.id.wode_tv_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wode_tv_rili:
                ActivityFactory.goJiludan(getActivity(), null);
                break;
            case R.id.wode_tv_time:
                if (PublicStaticData.isCanyin)
                    ActivityFactory.goRili(getActivity(), null);
                else
                    ActivityFactory.goYingyeTime(getActivity(), null);
                break;
            case R.id.wode_tv_shangjia:
                if (PublicStaticData.isCanyin)
                    ActivityFactory.goZiliaoCanyin(getActivity(), null);
                else
                    ActivityFactory.goZiliaoJingdian(getActivity(), null);
                break;
            case R.id.wode_tv_tongzhi:
                if (PublicStaticData.isCanyin)
                    ActivityFactory.goYingyeTime(getActivity(), null);
                else
                    ActivityFactory.goTongzhi(getActivity(), null);
                break;
            case R.id.wode_tv_tiaojian:
                    ActivityFactory.goQuxioa(getActivity(), null);
                break;
            case R.id.wode_tv_fankui:
                ActivityFactory.goYijian(getActivity(), null);
                break;
            case R.id.wode_tv_set:
                ActivityFactory.goSetting(getActivity(), null);
                break;
            case R.id.wode_iv_head:
                //ActivityFactory.goZiliaoJingdian(getActivity(),null);
                file = FileUtils.createImageFile(System.currentTimeMillis() + ".jpg");
                showPictureDialog();
                break;
            case R.id.wode_tv_name:
                break;
        }
    }

    private void showPictureDialog() {
        final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_choose_picture);
        dialog.findViewById(R.id.dialog_tv_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.storage(getActivity(), new PermissionUtils.OnPermissionResult() {
                    @Override
                    public void onGranted() {
                        chooseFromAlbum();
                        dialog.dismiss();
                    }
                });

            }
        });
        dialog.findViewById(R.id.dialog_tv_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.camera(getActivity(), new PermissionUtils.OnPermissionResult() {
                    @Override
                    public void onGranted() {
                        chooseFromCamera();
                    }
                });
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.dialog_tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void chooseFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果大于等于7.0使用FileProvider
            Uri uriForFile = FileProvider.getUriForFile(getActivity(), "xyd.com.bydshop.provider", file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, 1007);
        } else {
            startActivityForResult(intent, 1008);
        }
    }

    private void chooseFromCamera() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
            Uri uriForFile = FileProvider.getUriForFile(getActivity(), "xyd.com.bydshop.provider", file);
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
            intentFromCapture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intentFromCapture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        startActivityForResult(intentFromCapture, 1006);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param inputUri
     */
    public void startPhotoZoom(Uri inputUri) {

        if (inputUri == null) {
            Log.i("", "The uri is not exist.");
            return;
        }
        outputFile = FileUtils.createImageFile(System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent("com.android.camera.action.CROP");
        //sdk>=24
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            Uri outPutUri = Uri.fromFile(outputFile);
            intent.setDataAndType(inputUri, "image/*");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
            intent.putExtra("noFaceDetection", false);//去除默认的人脸识别，否则和剪裁匡重叠
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        } else {
            Uri outPutUri = Uri.fromFile(outputFile);
            if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                String url = GetImagePath.getPath(getActivity(), inputUri);//这个方法是处理4.4以上图片返回的Uri对象不同的处理方法
                intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
            } else {
                intent.setDataAndType(inputUri, "image/*");
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
        }

        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());// 图片格式
        startActivityForResult(intent, 1009);//这里就将裁剪后的图片的Uri返回了
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //7.0以下相册
        if (requestCode == 1008 && resultCode == getActivity().RESULT_OK) {
            startPhotoZoom(data.getData());
        }
        //7.0以上相册
        if (requestCode == 1007 && resultCode == getActivity().RESULT_OK) {
            File imgUri = new File(GetImagePath.getPath(getActivity(), data.getData()));
            Uri dataUri = FileProvider.getUriForFile(getActivity(), "xyd.com.bydshop.provider", imgUri);
            startPhotoZoom(dataUri);
        }
        //拍照
        if (requestCode == 1006 && resultCode == getActivity().RESULT_OK) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri inputUri = FileProvider.getUriForFile(getActivity(), "xyd.com.bydshop.provider", file);//通过FileProvider创建一个content类型的Uri
                startPhotoZoom(inputUri);//设置输入类型
            } else {
                Uri inputUri = Uri.fromFile(file);
                startPhotoZoom(inputUri);
            }
        }
        //裁剪
        if (requestCode == 1009 && resultCode == getActivity().RESULT_OK) {

            upImage();
        }
    }

    /**
     * 修改用户头像
     */
    private void upImage() {
        //构建body
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("apitoken", PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .addFormDataPart("avatar", outputFile.getName(), RequestBody.create(MediaType.parse("image/*"), outputFile))
                .build();

        BaseApi.getRetrofit()
                .create(MineApi.class)
                .avatar(requestBody)
                .compose(RxSchedulers.<BaseModel<AvatarModel>>compose())
                .subscribe(new BaseObserver<AvatarModel>() {
                    @Override
                    protected void onHandleSuccess(AvatarModel avatarModel, int code, String msg) {
                        ToastUtils.show(msg);
                        GlideUtil.getInstance().loadCircleImage(getActivity(), wodeIvHead, outputFile.getAbsolutePath());
                        PublicStaticData.sharedPreferences.edit().putString(PublicStaticData.AVATAR, PublicStaticData.URL + avatarModel.getAvatar()).commit();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                        outputFile.delete();
                        file.delete();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }


}
