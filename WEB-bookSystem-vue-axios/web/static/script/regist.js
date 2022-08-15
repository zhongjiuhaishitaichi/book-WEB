//# sourceURL=dynamicScript.js
function get(id) {
    return document.getElementById(id);
}

function preRegist() {
    debugger
    //标签对应
    var unameTxt = get("unameTxt");
    //用户名应为6~16位数组和字母组成
    //  1，/g 表示该表达式将用来在输入字符串中查找所有可能的匹配，返回的结果可以是多个。如果不加/g最多只会匹配一个
    //  2，/i  表示匹配的时候不区分大小写
    //  3，/m 表示多行匹配，什么是多行匹配呢？就是匹配换行符两端的潜在匹配。影响正则中的^$符号
    var unameReg = /^[a-zA-Z0-9]{6,16}$/;
    var uname = unameTxt.value;
    var unameSpan = get("unameSpan");//获得这句话 用户名应为6~16位数组和字母组成
    // 校验成功显示 反之不显示
    //校验test
    if (!unameReg.test(uname)) {
        //验证不通过 变为可见的 之前是hidden
        unameSpan.style.visibility = "visible";
        return false;
    } else {
        unameSpan.style.visibility = "hidden";
    }
    //密码 长度至少为8位
    var pwdTxt = get("pwdTxt");
    var pwd = pwdTxt.value;
    var pwdReg = /[\w]{8,}/;
    var pwdSpan = get("pwdSpan");
    if (!pwdReg.test(pwd)) {
        pwdSpan.style.visibility = "visible";
        return false;
    } else {
        pwdSpan.style.visibility = "hidden";
    }
    //两次密码不一致
    if (get("pwdTxt2").value != get("pwdTxt").value) {
        get("pwdSpan2").style.visibility = "visible";
        return false;
    } else {
        get("pwdSpan2").style.visibility = "hidden";
    }
    //输入正确的邮箱
    var email = get("emailTxt").value;
    var emailSpan = get("emailSpan");
    var emailReg = /^[a-zA-Z0-9_\.-]+@([a-zA-Z0-9-]+[\.]{1})+[a-zA-Z]+$/;
    if (!emailReg.test(email)) {
        emailSpan.style.visibility = "visible";
        return false;
    } else {
        emailSpan.style.visibility = "hidden";
    }
    return true;
}
/*
//如果想要发送异步请求，我们需要一个关键的对象 XMLHttpRequest
    var xmlHttpRequest ;

    function createXMLHttpRequest(){
        if(window.XMLHttpRequest){
            //符合DOM2标准的浏览器 ，xmlHttpRequest的创建方式
            xmlHttpRequest = new XMLHttpRequest() ;
        }else if(window.ActiveXObject){//IE浏览器
            try{
                xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            }catch (e) {
                xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP")
            }
        }
    }

    function ckUname(uname){
        createXMLHttpRequest();
        var url = "user.do?operate=ckUname&uname="+uname ;
        xmlHttpRequest.open("GET",url,true);
        //设置回调函数
        xmlHttpRequest.onreadystatechange = ckUnameCB ;
        //发送请求
        xmlHttpRequest.send();
    }

    function ckUnameCB(){
        if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
            //xmlHttpRequest.responseText 表示 服务器端响应给我的文本内容
            //alert(xmlHttpRequest.responseText);
            var responseText = xmlHttpRequest.responseText ;
            // {'uname':'1'}
            //alert(responseText);
            if(responseText=="{'uname':'1'}"){
                alert("用户名已经被注册！");
            }else{
                alert("用户名可以注册！");
            }
        }
    }
}*/
