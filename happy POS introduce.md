嘗試方向

**1. UI功能決定**

**2. 硬體連接(YT videos)**

### 課題:

:::info
如何簡化，以符合行動裝置需求:

![image](https://hackmd.io/_uploads/HkFebs7dT.png)

1. side menu 處理 (多個動態頁)
    * 操作人員負擔(需經常動操作)     
:::

:::success
**Project結構**

**以navigation結構儲存調整頁面**

![image](https://hackmd.io/_uploads/S1SbBjXup.png)

:::


### 首頁:

:::success
可動態新增其他功能(動態新增)
:::

![image](https://hackmd.io/_uploads/rkUvG3GO6.png)


---
### 前端頁面說明

:::info
**xml**

* start ->  登入頁
* registor -> 註冊頁
* adapter預覽畫面:
* operator_horizontal ->水平顯示首頁操作選項


:::

---

### 物件介紹

:::success
**Operation物件**
* name :操作名稱
* photo :操作圖片

**OperationAdapter -> 顯示物件到view**

**Strings.xml**
*由此更新服務選項*
* operation_set: -> 操作選項集，包含(結帳/商品/付款/管理)，可動態新增
* operation_image_set ->操作影像圖片集

**xml**

* 顯示子畫面operator_vertical.xml
    * txt_opr_name -> 顯示操作名稱
    * img_opr_img -> 顯示操作圖像
* 主畫面homefragment.xml
:::











