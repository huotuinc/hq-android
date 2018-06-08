package com.huotu.android.couponsleague.fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter

import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.adapter.QuanAdapter
import com.huotu.android.couponsleague.base.BaseFragment
import com.huotu.android.couponsleague.base.QuanCategoryEnum
import com.huotu.android.couponsleague.bean.Quan
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.widget.RecycleItemDivider
import com.shuyu.gsyvideoplayer.GSYVideoManager
import kotlinx.android.synthetic.main.fragment_quan_tab.*
import android.provider.MediaStore.Images
import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import com.huotu.android.couponsleague.bean.IdId
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloadQueueSet
import com.liulishuo.filedownloader.FileDownloader
import com.liulishuo.filedownloader.util.FileDownloadHelper
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuanTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class QuanTabFragment : BaseFragment<IPresenter>()
        , BaseQuickAdapter.OnItemChildClickListener
         {

    private var category: Int? = null
    private var quanAdapter:QuanAdapter?=null
    private var data = ArrayList<Quan>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getInt(ARG_CATEGORY, QuanCategoryEnum.ONE.id)

        }
    }


    override fun initView() {

        for(i in 0..10){

            var images = ArrayList<String>()
            if(i==0) {
                images.add("http://image.tkcm888.com/adSet_2018-06-04_d18eb67c0fbc43a398fc7c55f818122415281204839937212.png")
            }else if(i==1) {
                images.add("http://image.tkcm888.com/adSet_2018-06-04_d18eb67c0fbc43a398fc7c55f818122415281204839937212.png")
                images.add("http://image.tkcm888.com/adSet_2018-06-04_d18eb67c0fbc43a398fc7c55f818122415281204839937212.png")
            }else if(i==2){
                images.add("http://image.tkcm888.com/adSet_2018-06-04_d18eb67c0fbc43a398fc7c55f818122415281204839937212.png")
                images.add("http://image.tkcm888.com/adSet_2018-06-04_d18eb67c0fbc43a398fc7c55f818122415281204839937212.png")
                images.add("http://image.tkcm888.com/adSet_2018-05-31_56440f86ea1d4d60a9a4d725e26e62c015277545962763144.png")
            }else if(i>3){
                images.add("http://image.tkcm888.com/adSet_2018-06-04_d18eb67c0fbc43a398fc7c55f818122415281204839937212.png")
                images.add("http://image.tkcm888.com/adSet_2018-06-04_d18eb67c0fbc43a398fc7c55f818122415281204839937212.png")
                images.add("http://image.tkcm888.com/adSet_2018-05-31_56440f86ea1d4d60a9a4d725e26e62c015277545962763144.png")
                images.add("http://image.tkcm888.com/adSet_2018-05-31_a13475823f524d5f8b3b9480673e339915277602221601122.png")
            }

            data.add(Quan("","","❤只有一条路不不能选择不能选选择不能选择不能选选择不能选选择不能选选择不能选选择不能选选择不能选" +
                    "择能不能选择不能选择选不能选择不能选择不能选择不能选择不能选" +
                    "择不能选择择那就是放弃的路❤\r\n那只有一条路不能拒绝那就是成长的路收益者宝妈一枚！\uD83D\uDC4D",
                    images,"","11",0,"","",""))
        }

        quanAdapter = QuanAdapter(data)



        quan_tab_recyclerview.layoutManager=LinearLayoutManager(context)
        quan_tab_recyclerview.addItemDecoration(RecycleItemDivider(context!!,LinearLayoutManager.VERTICAL,5))

        quan_tab_recyclerview.adapter=quanAdapter


        quanAdapter!!.onItemChildClickListener = this


        quan_tab_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            internal var firstVisibleItem: Int = 0
            internal var lastVisibleItem: Int = 0

//            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                firstVisibleItem = ( recyclerView!!.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                lastVisibleItem = (recyclerView!!.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                //大于0说明有播放
                if (GSYVideoManager.instance().playPosition >= 0) {
                    //当前播放的位置
                    val position = GSYVideoManager.instance().playPosition
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().playTag == quanAdapter!!.TAG && (position < firstVisibleItem || position > lastVisibleItem)) {

                        //如果滑出去了上面和下面就是否，和今日头条一样
                        //是否全屏
                        if (!GSYVideoManager.isFullState( activity )) {
                            GSYVideoManager.releaseAllVideos()
                            quanAdapter!!.notifyDataSetChanged()
                        }
                    }
                }
            }
        })



    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        when(view!!.id){
            R.id.quan_item_one_share->{
                share()
            }
            R.id.quan_item_one_save_image->{

                var a= ArrayList<String>()
                a.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg")
                a.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3566.jpg")
                a.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3757.jpg")

                savaImage(a)
            }
        }
    }

    /**
     * 将图片存到本地
     */
    private fun savaImage(  images:ArrayList<String>){
            var downLoadQueueSet = FileDownloadQueueSet( object : FileDownloadListener(){
                override fun warn(task: BaseDownloadTask?) {

                }

                override fun completed(task: BaseDownloadTask?) {
                    if( (task!!.tag as IdId).id == (task!!.tag as IdId ).total){
                        toast("下载完成")
                    }
                }

                override fun pending(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {

                }

                override fun error(task: BaseDownloadTask?, e: Throwable?) {
                    toast("error")
                }

                override fun progress(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {

                }

                override fun paused(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {

                }
            })
            var tasks = ArrayList<BaseDownloadTask>()

        var dir = Environment.getExternalStorageDirectory().toString()+"/coupons/images/"
        var f = File(dir)
        f.delete()
        if(!f.exists()){
            f.parentFile.mkdir()
        }

        for(i in 0 until images.size){

            var name = (i+1).toString()+".jpg"
            var path = dir + name
            var idId = IdId(i, images.size-1)

            tasks.add( FileDownloader.getImpl().create( images[i] ).setTag( i+1 ).setPath(path).setTag(idId) )
        }
        downLoadQueueSet.disableCallbackProgressTimes()
        downLoadQueueSet.setAutoRetryTimes(1)
        downLoadQueueSet.downloadSequentially(tasks)//串行下载
        downLoadQueueSet.start()


//            try {
//                var dir= Environment.getExternalStorageDirectory().toString()+"/Coupons/"+picName+".jpg"
//                var f =  File(dir)
//                if (!f.exists()) {
//                    f.parentFile.mkdirs()
//                    f.createNewFile()
//                }
//                var out = FileOutputStream(f)
//                bm.compress(Bitmap.CompressFormat.PNG, 90, out)
//                out.flush()
//                out.close()
//                Uri uri = Uri.fromFile(f)
//                return uri;
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();    }
//            return null;


    }


    private fun share(){
        var intent = Intent(Intent.ACTION_SEND_MULTIPLE)
        intent.setType("image/*")
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM , getLocalImages())
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享")
        intent.putExtra(Intent.EXTRA_TEXT, "你好 ")
        intent.putExtra(Intent.EXTRA_TITLE, "我是标题")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        startActivity(Intent.createChooser(intent, "请选择"))
    }

    /**
     * 设置需要分享的照片放入Uri类型的集合里
     */
    private fun getLocalImages(): ArrayList<Uri> {
        val myList = ArrayList<Uri>()

        val imageDirectoryPath = Environment.getExternalStorageDirectory().toString() + "/coupons/images/"
        val dir = File(imageDirectoryPath)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        val imageDirectory = File(imageDirectoryPath)

        val fileList = imageDirectory.list()

        if (fileList.isNotEmpty() ) {

            var count = if( fileList.size>9) 9 else fileList.size

            for (i in 0 until count ) {

                try {

                    val values = ContentValues(7)

                    values.put(Images.Media.TITLE, fileList[i])

                    values.put(Images.Media.DISPLAY_NAME, fileList[i])

                    values.put(Images.Media.DATE_TAKEN, Date().time )

                    values.put(Images.Media.MIME_TYPE, "image/jpeg")

                    values.put(Images.ImageColumns.BUCKET_ID, imageDirectoryPath.hashCode())

                    values.put(Images.ImageColumns.BUCKET_DISPLAY_NAME, fileList[i])

                    values.put("_data", imageDirectoryPath + fileList[i])

                    val contentResolver = context!!.contentResolver

                    val uri = Uri.fromFile( File( imageDirectoryPath + fileList[i]) ) //contentResolver.insert(Images.Media.EXTERNAL_CONTENT_URI, values)

                    myList.add(uri)

                } catch (e: Exception) {

                    e.printStackTrace()

                }

            }

        }

        return myList
    }


    override fun fetchData() {

    }


    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_quan_tab
    }

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuanTabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance( category: Int ) =
                QuanTabFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_CATEGORY, category)

                    }
                }
    }
}
