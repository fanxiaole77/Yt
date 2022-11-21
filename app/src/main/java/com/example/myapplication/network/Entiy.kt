package com.example.myapplication.network

data class Login(
    val password: String,
    val username: String
)

data class Message(
    val code: Int,
    val msg: String,
    val token: String
)

data class Register(
    val idCard: String,
    val nickName: String,
    val password: String,
    val phonenumber: String,
    val sex: String,
    val userName: String
)

data class HomeBanner(
    val code: String,
    val msg: String,
    val rows: List<Row>,
    val total: String
) {
    data class Row(
        val advTitle: String,
        val advImg:String,
        val id: Int,
        val servModule: String,
        val sort: Int,
        val targetId: Int,
        val type: String
    )
}

data class HomeService(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val id: Int,
        val imgUrl: String,
        val isRecommend: String,
        val link: String,
        val serviceDesc: String,
        val serviceName: String,
        val serviceType: String,
        val sort: Int
    )
}

data class NewsType(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val appType: String,
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val name: String,
        val params: Params,
        val parentId: Any,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val status: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class NewsList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val commentNum: Int,
        val content: String,
        val cover: String,
        val hot: String,
        val id: Int,
        val likeNum: Int,
        val publishDate: String,
        val readNum: Int,
        val status: String,
        val subTitle: String,
        val tags: Any,
        val title: String,
        val top: String,
        val type: String
    )
}

data class NewContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val appType: String,
        val commentNum: Any,
        val content: String,
        val cover: String,
        val hot: String,
        val id: Int,
        val likeNum: Int,
        val publishDate: String,
        val readNum: Any,
        val status: String,
        val subTitle: String,
        val tags: Any,
        val title: String,
        val top: String,
        val type: String
    )
}

data class UserInfo(
    val code: Int,
    val msg: String,
    val user: User
) {
    data class User(
        val avatar: String,
        val balance: Double,
        val email: String,
        val idCard: String,
        val nickName: String,
        val phonenumber: String,
        val score: Int,
        val sex: String,
        val userId: Int,
        val userName: String
    )
}

data class User(
    val nickName: String,
    val phonenumber: String,
    val sex: String
)

data class Pass(
    val newPassword: String,
    val oldPassword: String
)
data class Feed(
    val content: String,
    val title: String
)
data class All(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: String,
        val end: String,
        val id: Int,
        val orderNum: String,
        val params: Params,
        val path: String,
        val payTime: Any,
        val paymentType: Any,
        val price: Double,
        val remark: Any,
        val searchValue: Any,
        val start: String,
        val status: Int,
        val updateBy: Any,
        val updateTime: Any,
        val userId: Int,
        val userName: String,
        val userTel: String
    ) {
        class Params
    }
}

data class GovBanner(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val title: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class GovType(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}
data class GovTpyeList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val appealCategoryId: Int,
        val appealCategoryName: Any,
        val content: String,
        val createBy: Any,
        val createTime: String,
        val detailResult: String,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val state: String,
        val title: String,
        val undertaker: String,
        val updateBy: Any,
        val updateTime: Any,
        val userId: Int
    ) {
        class Params
    }
}

data class GovContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val appealCategoryId: Int,
        val appealCategoryName: String,
        val content: String,
        val createBy: Any,
        val createTime: String,
        val detailResult: String,
        val id: Int,
        val imgUrl: Any,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val state: String,
        val title: String,
        val undertaker: String,
        val updateBy: Any,
        val updateTime: Any,
        val userId: Int
    ) {
        class Params
    }
}

data class App(
    val appealCategoryId: Int,
    val content: String,
    val title: String
)

data class BusList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: String,
        val end: String,
        val endTime: String,
        val first: String,
        val id: Int,
        val mileage: String,
        val name: String,
        val params: Params,
        val price: Double,
        val remark: Any,
        val searchValue: Any,
        val startTime: String,
        val updateBy: Any,
        val updateTime: String
    ) {
        class Params
    }
}

data class BusContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: String,
        val end: String,
        val endTime: String,
        val start: String,
        val first: String,
        val id: Int,
        val mileage: String,
        val name: String,
        val params: Params,
        val price: Double,
        val remark: Any,
        val searchValue: Any,
        val startTime: String,
        val updateBy: Any,
        val updateTime: String
    ) {
        class Params
    }
}

data class Bus(val start:String,val end:String,val price:String,val status:String)

data class NewsComment(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val appType: String,
        val commentDate: String,
        val content: String,
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val likeNum: Int,
        val newsId: Int,
        val newsTitle: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any,
        val userId: Int,
        val userName: String
    ) {
        class Params
    }
}

data class Comment(
    val content: String,
    val newsId: Int
)
data class Up(
    val code: Int,
    val fileName: String,
    val msg: String,
    val url: String
)