$(function() {
    var date = new Date();
    var myDate = date.toLocaleDateString();
    //牵头单位填报、修改
    $('.sure').click(function() {
        var fanganAll = $(".fangan-all").val();
        var jinduAll = $(".jindu-all").val();
        var anpaiAll = $(".anpai-all").val();
        var sle_add = $(".sle_add").val();
        if(fanganAll == '') {
            alert("请输入落实方案及举措");
            return false;
        } else if(jinduAll == '') {
            alert("进度安排");
            return false;
        } else if(anpaiAll == '') {
            alert("后续安排");
            return false;
        } else if(sle_add == 0) {
            alert("请选择任务状态");
            return false;
        }
        var onOff = true;
        $(".Allbutton").css("background", '#f0ad4e');
        $(".Allbutton").html("修改");
        $(".Allbutton").onOff = false;
        $(".listTwo-all").empty();
        $(".listTwo-all").append(fanganAll);
        $(".listThree-all").empty();
        $(".listThree-all").append(jinduAll);
        $(".listFive-all").empty();
        $(".listFive-all").append(anpaiAll);
        $(".listOne-all span").html(myDate)
    });
    //配合单位填报、修改
    $.each($('.peihe'), function(key, val) {
        $('.Yes').click(function() {
            var fangan = $(".fangan").val();
            var jindu = $(".jindu").val();
            var anpai = $(".anpai").val();
            if(fangan == '') {
                alert("请输入落实方案及举措");
                return false;
            } else if(jindu == '') {
                alert("进度安排");
                return false;
            } else if(anpai == '') {
                alert("后续安排");
                return false;
            }
            var onOff = true;
            $(".PHbutton").css("background", '#f0ad4e');
            $(".PHbutton").html("修改");
            $(".PHbutton").onOff = false;
            $(".listTwo").empty();
            $(".listTwo").append(fangan);
            $(".listThree").empty();
            $(".listThree").append(jindu);
            $(".listFive").empty();
            $(".listFive").append(anpai);
            $(".listOne span").html(myDate)
        })
    });
})