package com.example.su.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(200,"成功",null);
    }
    public static Result success(String msg,Object data){
        return new Result(200,msg,data);
    }
    public static Result result(Integer code,String msg,Object data){
        return new Result(code,msg,data);
    }
    public static Result error(Integer code,String msg){
        return new Result(code,msg,null);
    }
}
