window.onload = main;

function main() {
    //1.为数据行所有checkbox绑定onclick关联fun2
    var domArray = document.getElementsByTagName("input");
    for (var i = 1; i < domArray.length; i++) {
        domArray[i].onclick = fun2;
    }
    //2.为所有的数据行绑定onmouseover关联fun3
    domArray = document.getElementsByTagName("tr");
    for (var i =1 ; i<domArray.length;i++) {
        domArray[i].onmouseover = fun3;
        domArray[i].onmouseout = fun4;
    }

    //全选或全不选通过标题行的选中状态控制所有数据行的选中状态
    fun1 = function() {
        //获取所有行（标题行和数据行）checkbox对应的Dom对象
        var domArray = document.getElementsByTagName("input");
        //获取标题行checkbox选中状态
        var flag = domArray[0].checked;
        //对数据行所有的checkbox选中状态进行统一赋值
        for (let i = 1; i < domArray.length; i++) {
            domArray[i].checked = flag;
        }
    }

    // 全选或全不选 ---- 通过数据行选中状态控制标题行选中状态
    function fun2() {
        //获取所有行(标题行 + 数据行)checkbox对应的DOM对象
        var domArray = document.getElementsByTagName("input");
        //数据行被选中的checkbox的个数
        var checkedNum = 0;
        for (var i = 1; i < domArray.length; i++) {
            var dom = domArray[i];
            if (dom.checked == true) {
                checkedNum++;
            }
        }
        //判断
        if (checkedNum == domArray.length - 1) {
            domArray[0].checked = true;
        } else {
            domArray[0].checked = false;
        }
    }

    //鼠标悬停【数据行】背景颜色设置
    function fun3() {
        this.style.backgroundColor = "yellow";
    }

    //鼠标离开【数据行】背景颜色设置
    function fun4() {
        this.style.backgroundColor = "white";
    }
}