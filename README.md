# 批量更新（替换）文本文件中的指定字符串

## 用法：

1.准备好 replace.jar 和 replace.json

执行举例： java -jar -Dfile.encoding=utf-8 replace.jar replaceGit.json

### 批处理举例

```bat
echo off
::配置
call java -jar -Dfile.encoding=utf-8 replace.jar replaceGit.json
echo 程序执行完毕！
pause
```


### replace.json 格式举例1
    把文件名包含 vpn??.ovpn 的文件的aaa域名改成bbb域名

```json
{
  "dir": "C:\\Users\\yujing\\Desktop\\OpenVPN\\",
  "showLogFindFile": false,
  "showLogNoFindOld": false,
  "showLogChange": true,
  "showLogSaveFile": true,
  "FileReps": [
    {
      "fileName": "**/vpn??.ovpn",
      "reps": [
        {
          "old": [
            "remote aaa.com 994 #服务端IP及端口号"
          ],
          "rep": "remote bbb.com 994 #服务端IP及端口号"
        }
      ]
    }
  ]
}
```
### replace.json 格式举例2
    修改git地址，添加用户名密码

```json
{
    "dir": "/",
    "showLogFindFile": false,
    "showLogNoFindOld": false,
    "showLogChange": true,
    "showLogSaveFile": true,
    "FileReps": [
        {
            "fileName": "config",
            "reps": [
                {
                    "old": [
                        "url = https://git.xxx.cn/"
                    ],
                    "rep": "url = https://用户名:密码@git.xxx.cn/"
                }
            ]
        }
    ]
}
```
### replace.json 格式举例3
    更新okhttp3、更新gson、更新所有gradle项目的gradle版本

```json
{
  "dir": "/",
  "showLogFindFile": false,
  "showLogNoFindOld": false,
  "showLogChange": true,
  "showLogSaveFile": true,
  "FileReps": [
    {
      "fileName": "build.gradle",
      "reps": [
        {
          "old": [
            "'com.squareup.okhttp3:okhttp:4.2.2'",
            "'com.squareup.okhttp3:okhttp:4.9.0'",
            "'com.squareup.okhttp3:okhttp:4.9.1'",
            "'com.squareup.okhttp3:okhttp:4.9.2'"
          ],
          "rep": "'com.squareup.okhttp3:okhttp:4.9.3'"
        },
        {
          "old": [
            "'com.squareup.okhttp3:logging-interceptor:4.2.2'",
            "'com.squareup.okhttp3:logging-interceptor:4.9.0'",
            "'com.squareup.okhttp3:logging-interceptor:4.9.1'",
            "'com.squareup.okhttp3:logging-interceptor:4.9.2'"
          ],
          "rep": "'com.squareup.okhttp3:logging-interceptor:4.9.3'"
        },
        {
          "old": [
            "'com.google.code.gson:gson:2.8.5'",
            "'com.google.code.gson:gson:2.8.6'",
            "'com.google.code.gson:gson:2.8.7'",
            "'com.google.code.gson:gson:2.8.8'",
            "'com.google.code.gson:gson:2.8.9'"
          ],
          "rep": "'com.google.code.gson:gson:2.9.0'"
        }
      ]
    },
    {
      "fileName": "gradle-wrapper.properties",
      "reps": [
        {
          "old": [
            "services.gradle.org/distributions/gradle-3.0-all.zip",
            "services.gradle.org/distributions/gradle-3.1-all.zip",
            "services.gradle.org/distributions/gradle-3.2-all.zip",
            "services.gradle.org/distributions/gradle-3.2.1-all.zip",
            "services.gradle.org/distributions/gradle-3.3-all.zip",
            "services.gradle.org/distributions/gradle-3.4-all.zip",
            "services.gradle.org/distributions/gradle-3.4.4-all.zip",
            "services.gradle.org/distributions/gradle-3.5-all.zip",
            "services.gradle.org/distributions/gradle-3.5.1-all.zip",
            "services.gradle.org/distributions/gradle-4.0-all.zip",
            "services.gradle.org/distributions/gradle-4.0.1-all.zip",
            "services.gradle.org/distributions/gradle-4.1-all.zip",
            "services.gradle.org/distributions/gradle-4.2-all.zip",
            "services.gradle.org/distributions/gradle-4.2.1-all.zip",
            "services.gradle.org/distributions/gradle-4.3-all.zip",
            "services.gradle.org/distributions/gradle-4.3.1-all.zip",
            "services.gradle.org/distributions/gradle-4.4-all.zip",
            "services.gradle.org/distributions/gradle-4.4.1-all.zip",
            "services.gradle.org/distributions/gradle-4.5-all.zip",
            "services.gradle.org/distributions/gradle-4.5.1-all.zip",
            "services.gradle.org/distributions/gradle-4.6-all.zip",
            "services.gradle.org/distributions/gradle-4.7-all.zip",
            "services.gradle.org/distributions/gradle-4.8-all.zip",
            "services.gradle.org/distributions/gradle-4.9-all.zip",
            "services.gradle.org/distributions/gradle-4.10-all.zip",
            "services.gradle.org/distributions/gradle-4.10.1-all.zip",
            "services.gradle.org/distributions/gradle-5.0-all.zip",
            "services.gradle.org/distributions/gradle-5.1-all.zip",
            "services.gradle.org/distributions/gradle-5.1.1-all.zip",
            "services.gradle.org/distributions/gradle-5.2-all.zip",
            "services.gradle.org/distributions/gradle-5.2.1-all.zip",
            "services.gradle.org/distributions/gradle-5.3-all.zip",
            "services.gradle.org/distributions/gradle-5.3.1-all.zip",
            "services.gradle.org/distributions/gradle-5.4-all.zip",
            "services.gradle.org/distributions/gradle-5.4.1-all.zip",
            "services.gradle.org/distributions/gradle-5.5-all.zip",
            "services.gradle.org/distributions/gradle-5.5.1-all.zip",
            "services.gradle.org/distributions/gradle-5.6.1-all.zip",
            "services.gradle.org/distributions/gradle-5.6.2-all.zip",
            "services.gradle.org/distributions/gradle-5.6.3-all.zip",
            "services.gradle.org/distributions/gradle-5.6.4-all.zip",
            "services.gradle.org/distributions/gradle-6.0-all.zip",
            "services.gradle.org/distributions/gradle-6.0.1-all.zip",
            "services.gradle.org/distributions/gradle-6.1-all.zip",
            "services.gradle.org/distributions/gradle-6.1.1-all.zip",
            "services.gradle.org/distributions/gradle-6.2-all.zip",
            "services.gradle.org/distributions/gradle-6.3-all.zip",
            "services.gradle.org/distributions/gradle-6.4-all.zip",
            "services.gradle.org/distributions/gradle-6.4.1-all.zip",
            "services.gradle.org/distributions/gradle-6.5-all.zip",
            "services.gradle.org/distributions/gradle-6.5.1-all.zip",
            "services.gradle.org/distributions/gradle-6.6-all.zip",
            "services.gradle.org/distributions/gradle-6.6.1-all.zip",
            "services.gradle.org/distributions/gradle-6.7-all.zip",
            "services.gradle.org/distributions/gradle-6.7.1-all.zip",
            "services.gradle.org/distributions/gradle-6.8-all.zip",
            "services.gradle.org/distributions/gradle-6.8.1-all.zip",
            "services.gradle.org/distributions/gradle-6.8.2-all.zip",
            "services.gradle.org/distributions/gradle-6.8.3-all.zip",
            "services.gradle.org/distributions/gradle-6.9-all.zip",
            "services.gradle.org/distributions/gradle-6.9.1-all.zip",
            "services.gradle.org/distributions/gradle-6.9.2-all.zip",
            "services.gradle.org/distributions/gradle-7.0-all.zip",
            "services.gradle.org/distributions/gradle-7.0.1-all.zip",
            "services.gradle.org/distributions/gradle-7.0.2-all.zip",
            "services.gradle.org/distributions/gradle-7.1-all.zip",
            "services.gradle.org/distributions/gradle-7.1.1-all.zip",
            "services.gradle.org/distributions/gradle-7.2-all.zip",
            "services.gradle.org/distributions/gradle-7.3-all.zip",
            "services.gradle.org/distributions/gradle-7.3.3-all.zip",
            "services.gradle.org/distributions/gradle-7.4-all.zip",
            "services.gradle.org/distributions/gradle-7.4.1-all.zip",

            "services.gradle.org/distributions/gradle-3.0-bin.zip",
            "services.gradle.org/distributions/gradle-3.1-bin.zip",
            "services.gradle.org/distributions/gradle-3.2-bin.zip",
            "services.gradle.org/distributions/gradle-3.2.1-bin.zip",
            "services.gradle.org/distributions/gradle-3.3-bin.zip",
            "services.gradle.org/distributions/gradle-3.4-bin.zip",
            "services.gradle.org/distributions/gradle-3.4.4-bin.zip",
            "services.gradle.org/distributions/gradle-3.5-bin.zip",
            "services.gradle.org/distributions/gradle-3.5.1-bin.zip",
            "services.gradle.org/distributions/gradle-4.0-bin.zip",
            "services.gradle.org/distributions/gradle-4.0.1-bin.zip",
            "services.gradle.org/distributions/gradle-4.1-bin.zip",
            "services.gradle.org/distributions/gradle-4.2-bin.zip",
            "services.gradle.org/distributions/gradle-4.2.1-bin.zip",
            "services.gradle.org/distributions/gradle-4.3-bin.zip",
            "services.gradle.org/distributions/gradle-4.3.1-bin.zip",
            "services.gradle.org/distributions/gradle-4.4-bin.zip",
            "services.gradle.org/distributions/gradle-4.4.1-bin.zip",
            "services.gradle.org/distributions/gradle-4.5-bin.zip",
            "services.gradle.org/distributions/gradle-4.5.1-bin.zip",
            "services.gradle.org/distributions/gradle-4.6-bin.zip",
            "services.gradle.org/distributions/gradle-4.7-bin.zip",
            "services.gradle.org/distributions/gradle-4.8-bin.zip",
            "services.gradle.org/distributions/gradle-4.9-bin.zip",
            "services.gradle.org/distributions/gradle-4.10-bin.zip",
            "services.gradle.org/distributions/gradle-4.10.1-bin.zip",
            "services.gradle.org/distributions/gradle-5.0-bin.zip",
            "services.gradle.org/distributions/gradle-5.1-bin.zip",
            "services.gradle.org/distributions/gradle-5.1.1-bin.zip",
            "services.gradle.org/distributions/gradle-5.2-bin.zip",
            "services.gradle.org/distributions/gradle-5.2.1-bin.zip",
            "services.gradle.org/distributions/gradle-5.3-bin.zip",
            "services.gradle.org/distributions/gradle-5.3.1-bin.zip",
            "services.gradle.org/distributions/gradle-5.4-bin.zip",
            "services.gradle.org/distributions/gradle-5.4.1-bin.zip",
            "services.gradle.org/distributions/gradle-5.5-bin.zip",
            "services.gradle.org/distributions/gradle-5.5.1-bin.zip",
            "services.gradle.org/distributions/gradle-5.6.1-bin.zip",
            "services.gradle.org/distributions/gradle-5.6.2-bin.zip",
            "services.gradle.org/distributions/gradle-5.6.3-bin.zip",
            "services.gradle.org/distributions/gradle-5.6.4-bin.zip",
            "services.gradle.org/distributions/gradle-6.0-bin.zip",
            "services.gradle.org/distributions/gradle-6.0.1-bin.zip",
            "services.gradle.org/distributions/gradle-6.1-bin.zip",
            "services.gradle.org/distributions/gradle-6.1.1-bin.zip",
            "services.gradle.org/distributions/gradle-6.2-bin.zip",
            "services.gradle.org/distributions/gradle-6.3-bin.zip",
            "services.gradle.org/distributions/gradle-6.4-bin.zip",
            "services.gradle.org/distributions/gradle-6.4.1-bin.zip",
            "services.gradle.org/distributions/gradle-6.5-bin.zip",
            "services.gradle.org/distributions/gradle-6.5.1-bin.zip",
            "services.gradle.org/distributions/gradle-6.6-bin.zip",
            "services.gradle.org/distributions/gradle-6.6.1-bin.zip",
            "services.gradle.org/distributions/gradle-6.7-bin.zip",
            "services.gradle.org/distributions/gradle-6.7.1-bin.zip",
            "services.gradle.org/distributions/gradle-6.8-bin.zip",
            "services.gradle.org/distributions/gradle-6.8.1-bin.zip",
            "services.gradle.org/distributions/gradle-6.8.2-bin.zip",
            "services.gradle.org/distributions/gradle-6.8.3-bin.zip",
            "services.gradle.org/distributions/gradle-6.9-bin.zip",
            "services.gradle.org/distributions/gradle-6.9.1-bin.zip",
            "services.gradle.org/distributions/gradle-6.9.2-bin.zip",
            "services.gradle.org/distributions/gradle-7.0-bin.zip",
            "services.gradle.org/distributions/gradle-7.0.1-bin.zip",
            "services.gradle.org/distributions/gradle-7.0.2-bin.zip",
            "services.gradle.org/distributions/gradle-7.1-all.zip",
            "services.gradle.org/distributions/gradle-7.1.1-all.zip",
            "services.gradle.org/distributions/gradle-7.2-bin.zip",
            "services.gradle.org/distributions/gradle-7.3-bin.zip",
            "services.gradle.org/distributions/gradle-7.3.3-bin.zip",
            "services.gradle.org/distributions/gradle-7.4-bin.zip",
            "services.gradle.org/distributions/gradle-7.4.1-bin.zip"
          ],
          "rep": "services.gradle.org/distributions/gradle-7.4.2-bin.zip"
        }
      ]
    }
  ]
}
```

## IDEA中把项目打包成jar

  1.项目右键 ——> 构建模块

  2.文件菜单 ——> 项目结构 ——> 点击构建（工作）——> 点击+ ——> 选择jar ——> 选择来自具有依赖项的模块 ——> 选择main函数，选择提取到目标jar --> 点击应用

  3.菜单构建 ——> 构建工件... ——> 构建
  
  4.生成的jar目录：build\artifacts\replace_jar\***.jar



