package com.gout.xbmu.smartbulter.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gout.xbmu.smartbulter.R;
import com.gout.xbmu.smartbulter.entity.MyUser;
import com.gout.xbmu.smartbulter.ui.CourierActivity;
import com.gout.xbmu.smartbulter.ui.LoginActivity;
import com.gout.xbmu.smartbulter.ui.PhoneActivity;
import com.gout.xbmu.smartbulter.view.CenterDialog;
import com.gout.xbmu.smartbulter.view.CustomDialog;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigButton;
import com.mylhyl.circledialog.callback.ConfigDialog;
import com.mylhyl.circledialog.params.ButtonParams;
import com.mylhyl.circledialog.params.DialogParams;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.fragment
 * 文件名: ButlerFragment
 * Created by LanQinHui on 2017/8/13.
 * 描述： TODO
 */

public class UserFragment extends Fragment implements View.OnClickListener, CenterDialog.OnCenterItemClickListener {

    private Button etn_exit_user;
    private Button btn_update_ok;
    private TextView edit_user;
    private EditText et_username;
    private EditText et_age;
    private EditText et_desc;
    private LinearLayout rtn_233;
    private CircleImageView profile_image;
    private CustomDialog dialog;
    private TextView tv_phone;
    //快递
    private TextView tv_courier;

    private Button btn_camera;
    private Button btn_picture;
    private Button btn_cancel;

    private File tempFile = null;

    //自定义Dialog
    private CenterDialog centerDialog;
    
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,null);
        findView(view);
        return view;

    }

    //初始化View
    private void findView(View view) {
        etn_exit_user = (Button) view.findViewById(R.id.etn_exit_user);
        btn_update_ok = (Button) view.findViewById(R.id.btn_update_ok);
        edit_user = (TextView) view.findViewById(R.id.edit_user);

        et_username = (EditText) view.findViewById(R.id.et_username);
        et_age = (EditText) view.findViewById(R.id.et_age);
        et_desc = (EditText) view.findViewById(R.id.et_desc);
        tv_courier = (TextView) view.findViewById(R.id.tv_courier);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);

        rtn_233 = (LinearLayout) view.findViewById(R.id.rtn_233);
        profile_image = (CircleImageView) view.findViewById(R.id.profile_image);

        tv_courier.setOnClickListener(this);
        tv_phone.setOnClickListener(this);

        profile_image.setOnClickListener(this);

        edit_user.setOnClickListener(this);
        etn_exit_user.setOnClickListener(this);
        btn_update_ok.setOnClickListener(this);

        //初始化dialog
        dialog = new CustomDialog(getActivity(),500,500,R.layout.dialog_photo,R.style.Theme_dialog, Gravity.BOTTOM,R.style.pop_anim_style);
        //屏幕外点击无效
        dialog.setCancelable(false);

        btn_camera = (Button) dialog.findViewById(R.id.btn_camera);
        btn_picture = (Button) dialog.findViewById(R.id.btn_picture);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);

        btn_camera.setOnClickListener(this);
        btn_picture.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

        //默认不可点击、不可编辑
        setEnabled(false);

        //获取用户的信息
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        et_username.setText(userInfo.getUsername());
        et_age.setText(userInfo.getAge() + "");
        et_desc.setText((CharSequence) userInfo.getDesc());

        centerDialog = new CenterDialog(getActivity(), R.layout.dialog_layout,
                new int[]{R.id.dialog_cancel, R.id.dialog_sure});
        centerDialog.setOnCenterItemClickListener(this);


    }

    //控制焦点
    private void setEnabled(boolean is){
        et_username.setEnabled(is);
        et_age.setEnabled(is);
        et_desc.setEnabled(is);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.etn_exit_user:
//                //对话框提示退出登录
//                DialogUtil dialogUtil = new DialogUtil();
//                    dialogUtil.show(R.mipmap.ic_launcher, "确认退出登录吗？", new DialogButtonListener() {
//                        @Override
//                        public void sure() {
//
//                        }
//
//                        @Override
//                        public void cancel() {
//                            Toast.makeText(getActivity(),"点击了取消按钮",Toast.LENGTH_SHORT).show();
//                        }
//                    });

                centerDialog.show();
                //清除缓存的对象用户
                //MyUser.logOut();
                //现在的currentUser
                //BmobUser currentUser = MyUser.getCurrentUser();
                //跳转到登录页面
                //Toast.makeText(getActivity(),"退出登录成功,欢迎下次光临！",Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getActivity(), LoginActivity.class));
                //getActivity().finish();

                break;
            case R.id.edit_user:
                //编辑框可以加控制
                setEnabled(true);
                //修改按钮显示出来
                btn_update_ok.setVisibility(View.VISIBLE);
                //退出登录隐藏
                rtn_233.setVisibility(View.GONE);
                break;
            case R.id.btn_update_ok:
                //1、拿到输入框的值
                String username = et_username.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                //2、判断是否为空
                if(!TextUtils.isEmpty(username)&!TextUtils.isEmpty(age)){
                    //3、更改信息
                    MyUser user = new MyUser();
                    user.setUsername(username);
                    user.setAge(Integer.parseInt(age));
                    //4、判断简介是否为空
                    if(!TextUtils.isEmpty(desc)){
                        user.setDesc(desc);
                    }else {
                        user.setDesc("这位大侠来无影去无踪，什么都没有留下...");
                    }
                    //5、提交信息
                    BmobUser bmobUser = BmobUser.getCurrentUser();
                    user.update(bmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(getActivity(),"更新用户信息成功",Toast.LENGTH_SHORT).show();
                                //6、隐藏修改按钮显示退出按钮、并且调用setEnabled(false);
                                btn_update_ok.setVisibility(View.GONE);
                                rtn_233.setVisibility(View.VISIBLE);
                                setEnabled(false);
                                //7、处理逻辑  TODO  退出后更新记住的账号和密码
                            }else{
                                Toast.makeText(getActivity(),"更新用户信息失败"+ e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else {
                    Toast.makeText(getActivity(),"请完善你的信息，谢谢！",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.profile_image:
                //dialog.show();
                final String[] items = {"拍照", "从相册选择"};
                new CircleDialog.Builder(getActivity())
                        .configDialog(new ConfigDialog() {
                            @Override
                            public void onConfig(DialogParams params) {
                                //增加弹出动画
                                params.animStyle = R.style.dialogWindowAnim;
                            }
                        })
                        //.setTitle("标题")
                        //.setTitleColor(Color.BLUE)
                        .setItems(items, new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                               switch (position){
                                   case 0:
                                       //Toast.makeText(getActivity(), "拍照", Toast.LENGTH_SHORT).show();
                                       toCamera();
                                       //FaceUtil.openCamera(getActivity());
                                       break;
                                   case 1:
                                       //Toast.makeText(getActivity(), "从相册选择", Toast.LENGTH_SHORT).show();
                                       toPicture();
                                       //FaceUtil.choosePhoto(getActivity());
                                       break;
                               }
                            }
                        })
                        .setNegative("取消", null)
                        .configNegative(new ConfigButton() {
                            @Override
                            public void onConfig(ButtonParams params) {
                                //取消按钮字体颜色
                                params.textColor = Color.RED;
                            }
                        })
                        .show();
                break;

            case R.id.btn_cancel:
                dialog.dismiss();
                break;
            case R.id.btn_camera:
                toCamera();
                break;
            case R.id.btn_picture:
                toPicture();
                break;
            case R.id.tv_courier:
                startActivity(new Intent(getActivity(), CourierActivity.class));
                break;
            case R.id.tv_phone:
                startActivity(new Intent(getActivity(),PhoneActivity.class));
                break;
        }
    }

    public static final String PHOTO_IMAGE_FILE_NAME = "fileImg.jpg";
    public static final  int CAMERA_REQUEST_CODE = 100;
    public static final  int IMAGE_REQUEST_CODE = 101;
    public static final  int RESULT_REQUEST_CODE = 102;


    //开启相册
    private  void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_REQUEST_CODE);
        dialog.dismiss();
    }

    //开启相机
    private  void toCamera()  {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断内存卡是否可用
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(),PHOTO_IMAGE_FILE_NAME)));
        startActivityForResult(intent,CAMERA_REQUEST_CODE);

        dialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != getActivity().RESULT_CANCELED){
            switch (requestCode){
                //相册返回的数据
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                //相机返回的数据
                case CAMERA_REQUEST_CODE:
                    tempFile = new File(Environment.getExternalStorageDirectory(),PHOTO_IMAGE_FILE_NAME);
                    startPhotoZoom(Uri.fromFile(tempFile));
                    break;
                case RESULT_REQUEST_CODE:
                    //有可能舍弃
                    if(data !=null){
                        //拿到图片裁剪设置
                        setImageToView(data);
                        //删除裁剪前的图片
                        if(tempFile != null){
                            tempFile.delete();
                        }
                    }
                    break;
            }

        }
    }

    //裁剪的方法
    private void startPhotoZoom(Uri uri){
        if(uri == null){
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"img/*");
        //设置裁剪
        intent.putExtra("crop","true");
        //裁剪宽高比例
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        //裁剪的图片质量
        intent.putExtra("outputX",320);
        intent.putExtra("outputY",320);
        startActivityForResult(intent,RESULT_REQUEST_CODE);
    }

    //设置图片
    private void setImageToView(Intent data){
        Bundle bundle = data.getExtras();
        if(bundle != null){
            Bitmap bitmap = bundle.getParcelable("data");
            profile_image.setImageBitmap(bitmap);
        }

    }

    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view) {
        switch (view.getId()){
            case R.id.dialog_sure:
                //清除缓存的对象用户
                MyUser.logOut();
                //现在的currentUser
                BmobUser currentUser = MyUser.getCurrentUser();
                //跳转到登录页面
                Toast.makeText(getActivity(),"退出登录成功,欢迎下次光临！",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.dialog_cancel:
                break;

        }

    }


}
