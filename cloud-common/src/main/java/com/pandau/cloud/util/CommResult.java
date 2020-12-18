package com.pandau.cloud.util;

import com.pandau.cloud.entities.Result;


public class CommResult {
      public static Result ok(Object data,String msg){
          return  new Result(200,msg,data);
      }
      public static Result error(Integer code,String msg){
          return  new Result(code,msg,null);
      }
}
