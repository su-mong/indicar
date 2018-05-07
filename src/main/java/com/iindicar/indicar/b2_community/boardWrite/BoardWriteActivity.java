package com.iindicar.indicar.b2_community.boardWrite;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.ObservableInt;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Toast;

import com.commit451.teleprinter.Teleprinter;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.vo.WriteFileVO;
import com.iindicar.indicar.databinding.BoardWriteActivityBinding;
import com.iindicar.indicar.utils.CustomAlertDialog;
import com.iindicar.indicar.utils.IPickPhotoHelper;
import com.iindicar.indicar.utils.PickPhotoHelper;

import java.util.ArrayList;
import java.util.List;

import io.apptik.multiview.layoutmanagers.ViewPagerLayoutManager;

public class BoardWriteActivity extends BaseActivity<BoardWriteActivityBinding> implements BoardWriteNavigator {

    private final int PICK_FROM_ALBUM = 0;
    private final int PICK_FROM_CAMERA = 1;

    private Boolean isUpdating = false;
    private BoardWriteViewModel viewModel;
    private BoardWriteAdapter adapter;

    private PickPhotoHelper pickPhotoHelper = new PickPhotoHelper(this);

    @Override
    public void onBackPressed() {
        onCancelWrite();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.board_write_activity;
    }

    @Override
    protected void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId) {
        centerImageId.set(R.drawable.logo_write);
        leftImageId.set(R.drawable.ic_action_close);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPermission();

        viewModel = new BoardWriteViewModel();
        viewModel.setNavigator(this);

        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        viewModel.boardVO.setUserId(prefLogin.getString("_id", "admin"));
        viewModel.boardVO.setUserName(prefLogin.getString("name", "admin"));

        Intent intent = getIntent();
        this.isUpdating = intent.getBooleanExtra("isUpdating", false);

        if(isUpdating){
            viewModel.isIntroPage.set(false);
            leftImageId.set(R.drawable.ic_action_close);
        }

        actionBarBinding.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancelWrite();
            }
        });

        binding.setViewModel(viewModel);
        binding.intro.setBoard(viewModel.boardVO);
        viewModel.start();

        initWritePageView();
    }

    private void initWritePageView() {
        new Teleprinter(this)
                .addKeyboardToggleListener(new Teleprinter.OnKeyboardToggleListener() {
            @Override
            public void onKeyboardShown(int keyboardSize) {
                binding.buttonContainer.setVisibility(View.GONE);
            }

            @Override
            public void onKeyboardClosed() {
                binding.buttonContainer.setVisibility(View.VISIBLE);
            }
        });

        adapter = new BoardWriteAdapter(this);
        adapter.setAlbumButtonClickListener(new BoardWriteAdapter.AlbumButtonClickListener() {
            @Override
            public void onClick(View view, int position) {
                processPickPhoto(position, PICK_FROM_ALBUM);
            }
        });
        adapter.setCameraButtonClickListener(new BoardWriteAdapter.CameraButtonClickListener() {
            @Override
            public void onClick(View view, int position) {
                processPickPhoto(position, PICK_FROM_CAMERA);
            }
        });
        adapter.setImageButtonClickListener(new BoardWriteAdapter.ImageButtonClickListener() {
            @Override
            public void onClick(View view, int position) {
                showPhotoDialog(position);
            }
        });
        adapter.setTextChangedListener(new BoardWriteAdapter.TextChangedListener() {
            @Override
            public void onTextChanged(String text, int position) {
                adapter.getItem(position).setWriteText(text);
            }
        });
        binding.detail.pageContainer.setLayoutManager(new ViewPagerLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.detail.pageContainer.setAdapter(adapter);

        if(isUpdating) {
            getUpdateItems();
        } else {
            viewModel.addNewPage(binding.detail.pageContainer);
        }
    }

    private void getUpdateItems() {

        Intent intent = getIntent();

        viewModel.boardVO = intent.getParcelableExtra("updateBoard");
        List<WriteFileVO> updateItemList = intent.getParcelableArrayListExtra("updateItems");

        adapter.addItems(updateItemList);
    }

    @Override
    public Intent getActivityIntent() {
        return getIntent();
    }

    // go to photo and content view page
    @Override
    public void onPageChangedToDetail() {
        leftImageId.set(R.drawable.btn_back);

        actionBarBinding.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.isIntroPage.set(true);
                onPageChangedToIntro();
            }
        });
    }

    // go to car filter and board type page
    @Override
    public void onPageChangedToIntro() {
        leftImageId.set(R.drawable.ic_action_close);

        actionBarBinding.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancelWrite();
            }
        });
    }

    // cancel
    @Override
    public void onCancelWrite() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        final CustomAlertDialog dialog = new CustomAlertDialog(this);
        dialog.setTitle("글 작성을 그만두시겠습니까?\n작성한 내용은 모두 삭제됩니다.")
                .setSubTitle("All content you write will be deleted")
                .setPositiveButtonListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onFinishActivity();
                    }
                })
                .setSize((int) (size.x * 0.9), (int) (size.y * 0.25))
                .show();
    }

    @Override
    public void onSubmitWrite() {

        // 사진을 등록하지 않은 페이지 체크
        for (int i = 0 ; i < adapter.getItemCount() ; i++){
            WriteFileVO vo = adapter.getItem(i);

            if(vo.getFilePath() == null || vo.getFilePath().equals("")){
                Toast.makeText(this, "사진을 등록해주세요.", Toast.LENGTH_SHORT).show();
                binding.detail.pageContainer.smoothScrollToPosition(i);
                return;
            }
        }

        /** TODO (2018.05.04) 화면 크기 구하는 코드 - CustomAlertDialog 로 집어넣기 */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        final CustomAlertDialog dialog = new CustomAlertDialog(this);
        dialog.setTitle("게시물을 등록하시겠습니까?")
                .setSubTitle("Upload this post.")
                .setPositiveButtonListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewModel.startUpload(getWriteItems());
                        dialog.dismiss();
                    }
                })
                .setSize((int) (size.x * 0.9), (int) (size.y * 0.25))
                .show();
    }

    @Override
    public void onFinishActivity() {
        setResult(RESULT_OK, new Intent());
        finish();
        overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }

    @Override
    public void showTestToast(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        pickPhotoHelper.onActivityResult(requestCode, resultCode, data);
    }

    private void getPermission(){

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();

    }

    @Override
    public List<WriteFileVO> getWriteItems() {
        return adapter.getItemList();
    }


    public void showPhotoDialog(final int position){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        final CustomAlertDialog dialog = new CustomAlertDialog(this);
        dialog.setImageId(R.drawable.button_trash)
                .setTitle("사진을 정말로 삭제하시겠습니까?")
                .setSubTitle("delete this photo.")
                .setPositiveButtonListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removePhoto(position);
                        dialog.dismiss();
                    }
                })
                .setSize((int) (size.x * 0.9), (int) (size.y * 0.25))
                .show();
    }

    public void removePhoto(int position){
        WriteFileVO vo =  adapter.getItem(position);
        vo.setImageUrl(null);
        vo.setFilePath("");
        adapter.notifyItemChanged(position);
    }

    private void processPickPhoto(final int position, int commend) {

        if(commend == PICK_FROM_ALBUM){

            pickPhotoHelper.pickFromAlbum(new IPickPhotoHelper.loadPhotoListCallBack<Uri>() {
                @Override
                public void onPhotoListLoaded(List<Uri> photoUriList) {

                    final int IMAGE_COUNT = photoUriList.size(); // 리스트 개수

                    ArrayList<WriteFileVO> listFromAlbum = new ArrayList<>();

                    for(int i = 0 ; i < IMAGE_COUNT ; i++){
                        WriteFileVO vo = new WriteFileVO();
                        vo.setImageUrl(photoUriList.get(i));
                        vo.setFilePath(getRealPathFromURI(photoUriList.get(i)));
                        listFromAlbum.add(vo);
                    }

                    // 첫번째 사진은 리스트에서 빼고 기존 어댑터에 있던 페이지에 붙인다.
                    WriteFileVO firstPhoto = listFromAlbum.remove(0);
                    adapter.getItem(position).setImageUrl(firstPhoto.getImageUrl());
                    adapter.getItem(position).setFilePath(firstPhoto.getFilePath());
                    adapter.notifyItemChanged(position);

                    // 나머지 사진은 첫번째 사진의 페이지 뒤에 추가
                    adapter.addItems(position + 1, listFromAlbum);
                }

                @Override
                public void onPhotoNotAvailable() {
                    Log.e("BoardWriteAdapter", "processPickPhoto() PICK_FROM_ALBUM " + R.string.photo_from_storage_not_available);
                }
            });

        } else if(commend == PICK_FROM_CAMERA){

            pickPhotoHelper.pickFromCamera(new IPickPhotoHelper.loadPhotoCallBack<Uri>() {
                @Override
                public void onPhotoLoaded(Uri photoUri) {
                    adapter.getItem(position).setImageUrl(photoUri);
                    adapter.getItem(position).setFilePath(getRealPathFromURI(photoUri));
                    adapter.notifyItemChanged(position);
                }

                @Override
                public void onPhotoNotAvailable() {
                    Log.e("BoardWriteAdapter", "processPickPhoto() PICK_FROM_CAMERA " + R.string.photo_from_storage_not_available);
                }
            });
        }
    }

    // change Uri to file's real path (절대경로)
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

}
