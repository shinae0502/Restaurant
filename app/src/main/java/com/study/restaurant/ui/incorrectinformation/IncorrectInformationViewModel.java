package com.study.restaurant.ui.incorrectinformation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.study.restaurant.util.Logger;

public class IncorrectInformationViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    String titleBar;

    public MutableLiveData<Boolean> closeBusiness = new MutableLiveData<>();
    public MutableLiveData<Boolean> wrongStoreName = new MutableLiveData<>();
    public MutableLiveData<Boolean> wrongStoreAddress = new MutableLiveData<>();
    public MutableLiveData<Boolean> wrongTel = new MutableLiveData<>();
    public MutableLiveData<Boolean> wrongLocation = new MutableLiveData<>();
    public MutableLiveData<Boolean> wrongConvenienceInformation = new MutableLiveData<>();
    public MutableLiveData<Boolean> wrongPrice = new MutableLiveData<>();
    public MutableLiveData<Boolean> wrongMenu = new MutableLiveData<>();
    public MutableLiveData<Boolean> wrongOther = new MutableLiveData<>();
    public MutableLiveData<Boolean> selectIncorrectInformation = new MutableLiveData<>();

    public String getTitleBar() {
        return titleBar;
    }

    public void setTitleBar(String titleBar) {
        this.titleBar = titleBar;
    }

    public void clickCloseBusiness() {
        Logger.v("");
        closeBusiness.setValue(true);
        wrongStoreName.setValue(false);
        wrongStoreAddress.setValue(false);
        wrongTel.setValue(false);
        wrongLocation.setValue(false);
        wrongConvenienceInformation.setValue(false);
        wrongPrice.setValue(false);
        wrongMenu.setValue(false);
        wrongOther.setValue(false);
        selectIncorrectInformation.setValue(true);
    }

    public void clickWrongStoreName() {
        Logger.v("");
        closeBusiness.setValue(false);
        wrongStoreName.setValue(true);
        wrongStoreAddress.setValue(false);
        wrongTel.setValue(false);
        wrongLocation.setValue(false);
        wrongConvenienceInformation.setValue(false);
        wrongPrice.setValue(false);
        wrongMenu.setValue(false);
        wrongOther.setValue(false);
        selectIncorrectInformation.setValue(true);
    }

    public void clickWrongStoreAddress() {
        Logger.v("");
        closeBusiness.setValue(false);
        wrongStoreName.setValue(false);
        wrongStoreAddress.setValue(true);
        wrongTel.setValue(false);
        wrongLocation.setValue(false);
        wrongConvenienceInformation.setValue(false);
        wrongPrice.setValue(false);
        wrongMenu.setValue(false);
        wrongOther.setValue(false);
        selectIncorrectInformation.setValue(true);
    }

    public void clickWrongTel() {
        Logger.v("");
        closeBusiness.setValue(false);
        wrongStoreName.setValue(false);
        wrongStoreAddress.setValue(false);
        wrongTel.setValue(true);
        wrongLocation.setValue(false);
        wrongConvenienceInformation.setValue(false);
        wrongPrice.setValue(false);
        wrongMenu.setValue(false);
        wrongOther.setValue(false);
        selectIncorrectInformation.setValue(true);
    }

    public void clickWrongLocation() {
        Logger.v("");
        closeBusiness.setValue(false);
        wrongStoreName.setValue(false);
        wrongStoreAddress.setValue(false);
        wrongTel.setValue(false);
        wrongLocation.setValue(true);
        wrongConvenienceInformation.setValue(false);
        wrongPrice.setValue(false);
        wrongMenu.setValue(false);
        wrongOther.setValue(false);
        selectIncorrectInformation.setValue(true);
    }

    public void clickWrongConvenienceInformation() {
        Logger.v("");
        closeBusiness.setValue(false);
        wrongStoreName.setValue(false);
        wrongStoreAddress.setValue(false);
        wrongTel.setValue(false);
        wrongLocation.setValue(false);
        wrongConvenienceInformation.setValue(true);
        wrongPrice.setValue(false);
        wrongMenu.setValue(false);
        wrongOther.setValue(false);
        selectIncorrectInformation.setValue(true);
    }

    public void clickWrongPrice() {
        Logger.v("");
        closeBusiness.setValue(false);
        wrongStoreName.setValue(false);
        wrongStoreAddress.setValue(false);
        wrongTel.setValue(false);
        wrongLocation.setValue(false);
        wrongConvenienceInformation.setValue(false);
        wrongPrice.setValue(true);
        wrongMenu.setValue(false);
        wrongOther.setValue(false);
        selectIncorrectInformation.setValue(true);
    }

    public void clickWrongMenu() {
        Logger.v("");
        closeBusiness.setValue(false);
        wrongStoreName.setValue(false);
        wrongStoreAddress.setValue(false);
        wrongTel.setValue(false);
        wrongLocation.setValue(false);
        wrongConvenienceInformation.setValue(false);
        wrongPrice.setValue(false);
        wrongMenu.setValue(true);
        wrongOther.setValue(false);
        selectIncorrectInformation.setValue(true);
    }

    public void clickWrongOther() {
        Logger.v("");
        closeBusiness.setValue(false);
        wrongStoreName.setValue(false);
        wrongStoreAddress.setValue(false);
        wrongTel.setValue(false);
        wrongLocation.setValue(false);
        wrongConvenienceInformation.setValue(false);
        wrongPrice.setValue(false);
        wrongMenu.setValue(false);
        wrongOther.setValue(true);
        selectIncorrectInformation.setValue(true);
    }

    public void sendIncorrectInformation(View v) {

    }
}
