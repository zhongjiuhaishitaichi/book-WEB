

//页面加载时调用
window.onload = function () {
    var vue = new Vue({
        el: "#cart_div",
        data: {
            cart:{}
        },
        methods: {
            getCart: function () {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        //执行cartInfo方法
                        operate: "cartInfo"
                    }
                })
                    .then(function (value) {
                        //cart 赋给vue的data.cart
                            var cart=value.data;
                            vue.cart=cart;

                    }).catch(function (reason) {
                })
            },
        editCart:function (cartItemId,buyCount){
            axios({
                method: "POST",
                url: "cart.do",
                params: {//这里用params 才能和controller对应
                    //执行cartInfo方法
                    operate: "editCart",
                    cartItemId:cartItemId,
                    buyCount:buyCount
                }
            })
                .then(function (value) {
                    //重新更新一下
                    vue.getCart();
                }).catch(function (reason) {
            })

        }
        },
        mounted: function () {
            //调用
            this.getCart();
        }
    });

}