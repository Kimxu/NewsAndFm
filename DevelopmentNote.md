歌曲图片加载

这里使用百度云音乐做加载，先去查询歌曲，

http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.6.1&channel=360safe&operator=3&method=baidu.ting.search.catalogSug&format=json&query=%E5%B8%9D%E9%83%BD

之后获得歌曲id

在用

http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.6.1&channel=360safe&operator=3&method=baidu.ting.song.getInfos&format=json&songid=416996&ts=1451464605101&e=bLkVYIBl6FyH5SR9BbVH5Iv7HxAMs0b4xqQNRrBDXPwPoEE6uYJc4pqcgxu%2Bsjft&nw=2&ucf=1&res=1&l2p=0&lpb=&usup=1&lebo=0

根据歌曲id去获得歌曲的图片

# 注意事项

1. 现在是把歌曲列表存在Application里面。每次更新都更新Application中的列表。
2. Notification更新需要notify下，不然不更新。这个坑测试了半天。




# 开发笔记

歌曲的状态这里，采用的是在播放的Activity里面对Service中播放的歌曲回调。

但是这么做估计会遇见一些问题，比如说这个Activity挂掉了。。。Service的回调就不好使了。

因为要考虑歌曲状态的回调统一性，索性就放在Application中回调（未实现代码），之后同过广播发送，如果播放页面的

Activity接收到了广播就进行相关的处理。在对通知栏的播放状态进行处理。

这里还涉及到一个问题，就是播放器页面的数据处理。应该每次启动的时候都去Service中获取歌曲的状态进行处理。

在播放界面返回上一级别菜单，会报错，因为服务一直在回调，但是界面已经释放掉了。

需要判断控件是否为空。



--- 解决

//控制通知栏的播放状态 监控广播（完成）

 mApplication.setNotificationStatus(mState);



--- 如果是全局调用的地方就放在Application（完成）

--- 播放音乐还不同步，应该是点击播放列表就开始播放，如果点击的是同一首歌曲，就不改变播放状态



-- 2016.1.06

歌词下载到本机了。代码需要优化下。主要是接口太乱了。。。。。醉了

---2016.1.07

歌词加载不出来，路径对了。这里需要优化下 （解决）

封面加载进去了，逻辑需要改进 

—2016.1.09

歌单和排行添加进去了

—2016.1.11

 歌曲推荐

http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.6.1&channel=360safe&operator=3&method=baidu.ting.song.getEditorRecommend&format=json&num=6

热门歌单

http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.6.1&channel=360safe&operator=3&method=baidu.ting.diy.getHotGeDanAndOfficial&format=json&num=6

电台节目

http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.6.1&channel=360safe&operator=3&method=baidu.ting.radio.getRecommendRadioList&format=json&num=6

风格

http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.6.1&channel=360safe&operator=3&method=baidu.ting.scene.getSugScene

King榜单

http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.6.1&channel=360safe&operator=3&method=baidu.ting.plaza.king

导航

http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.6.1&channel=360safe&operator=3&method=baidu.ting.plaza.getFocusPic&format=json&num=6

导航点击跳转

http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.lebo.getAlbumDetail&start=0&did=&apiver=2&from=leboandroid&app=121&bduss=&args=baseinfo%2Cstatistics%2Cpic%2Ctag%2Csonglist.song.baseinfo%2Csonglist.song.pic%2Csonglist.song.statistics&song_id=&album_id=12699507&terminal=android&clientver=5.6.6.1&imei=&uid=&sysver=4.0&sort=&channel=&limit=20



接口界面设计到一半，就没完成了。明天继续







































