package com.example.xiaojun.lingdaotanchuang.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by chenjun on 2017/4/17.
 */
@Entity
public class MoShengRenBean  {
    @Id@NotNull
    private Long id;

    private  String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public MoShengRenBean() {
    }

    @Generated(hash = 1301799245)
    public MoShengRenBean(@NotNull Long id, String url) {
        this.id = id;
        this.url = url;
    }




//    @Override
//    public boolean equals(Object obj) {
//        if(obj == null) return false;
//        if(this == obj) return true;
//        if(obj instanceof MoShengRenBean){
//            MoShengRenBean user =(MoShengRenBean)obj;
////          if(user.id = this.id) return true; // 只比较id
//            // 比较id和username 一致时才返回true 之后再去比较 hashCode
//            if(user.id == this.id //&& user.username.equals(this.username)
//                                   )
//                return true;
//        }
//        return false;
//    }
//
//    /**
//     * 重写hashcode 方法，返回的hashCode 不一样才认定为不同的对象
//     */
//    @Override
//    public int hashCode() {
//      return id.hashCode(); // 只比较id，id一样就不添加进集合
//     //   return id.hashCode() * username.hashCode();
//    }
}
