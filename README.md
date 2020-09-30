# Android全版本权限请求库[RequestPermission]
github地址:[：https://github.com/Cuinings/AopPermission]

## ==注意：目前仅可使用Java部分API，kotlin部分还在调整中==
## 使用说明
### 第一步添加maven
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### 第二部在moudle的build.gradle中添加
```
implementation 'com.github.Cuinings:AopPermission:1.0.0'
```

### 使用方式
在方法体上使用注解@permission(value = {permission...}, requestCoe = 100)
```
@Permission(value = {
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CALL_PHONE
}, requestCode = 200)
private void request() {
    //这里是权限通过后才会执行
    Toast.makeText(this, "申请成功", Toast.LENGTH_SHORT).show();
}
```
如果你需要请求失败结果回调可以使用以下方式：

```
@PermissionCancel(requestCode = 200)
public void cancel() {
    Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
}

@PermissionDenied(requestCode = 200)
public void denied() {
    Toast.makeText(this, "已拒绝", Toast.LENGTH_SHORT).show();
}
```
