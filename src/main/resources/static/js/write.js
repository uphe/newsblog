$(function () {
    // 单击按钮弹出模态框
    $(".publish").on("click", function () {
        $(".modal").show();
        $(".mask").show()
    })
    // 点击X关闭模态框
    $(".modal-content-head").children("button").on("click", function () {
        $(".modal").hide();
        $(".mask").hide()
    })
    // 添加文章标签弹出模态框
    $(".mark-selection .add").on("click", function () {
        $(".mark-selection-box").show()
    })
    $(".mark-selection-box .close").on("click", function () {
        $(".mark-selection-box").hide()
    })
    //添加文章标签
    // 在输入框中添加标签

    function addtag() {
        // 新建标签
        $(".mark-selection-box-input input").on("keyup", function (e) {
            if ($(this).val() !== "" && e.keyCode == 13) {
                var content = $('<button class="add-tag add-tag-tags"><span>' + $(this).val() + '</span><i class="add-tag-close">x<i></button>')
                $(".mark-selection").prepend(content);
                // console.log(content.attr("data-index"));
                $(this).val("");
                tagChanged();
                // 为新添加的标签X绑定点击事件
                removeTag($(".add-tag-close"),tagChanged);

            }
        })
        // 显示已存在标签
        // 1. 排他思想 添加current类名
        $('.tags-items li').click(function () {
            $(this).addClass('isactive').siblings().removeClass('isactive');
            // 2. 点击的同时得到当前li的索引号 方便显示内容的匹配
            var index = $(this).index();
            // 3. 显示对应内容
            $('.tag-items-content div').eq(index).show().siblings().hide();
        })

        // 添加已存在标签
        $(".tag-items-content div li").unbind('click').click(function () {
            var content = $('<button class="add-tag add-tag-tags"><span>' + $(this).text() + '</span><i class="add-tag-close">x<i></button>')
            $(".mark-selection").prepend(content);
            tagChanged();
            removeTag($(".add-tag-close"), tagChanged);
        })
    }

    function removeTag(element, callback) {
        // 点击x移除
        element.unbind('click').click(function (e) {
            $(".tag-options-list span").each(function() {
                console.log($(this).text());
                console.log($(e.target).siblings("span").text())
                if($(this).text()==$(e.target).siblings("span").text()) {
                    $(this).siblings(".checkbox").prop("checked",false)
                }
            })
            $(this).parent().remove();
            callback();
        })

    }
    function tagChanged() {
        if ($(".add-tag-tags").length == 5) {
            // alert("hello")
            // 解绑键入事件
            $(".mark-selection-box-input input").off();
            // 隐藏添加标签按钮
            $(".mark-selection .add").hide();
            $(".tag-num").hide();
            $(".max-tag-num").show();
        } else {
            $(".mark-selection .add").show();
            $(".tag-num").show();
            $(".max-tag-num").hide();
            addtag();
            //  alert(5 - $(".add-tag").length)
            var num = 5 - $(".add-tag-tags").length;
            $(".mark-selection-box-selectTag .tag-num").text('还可添加' + num + '个标签');
        }
    }
    addtag();
    // 新建分类专栏
    $(".right .new-class").on("click", function () {
        var content = $('<button class="add-tag add-tag-class"><span contenteditable="true"></span><i class="add-tag-close">x<i></button>')
        $(".right .new-class").before(content);
        classChanged();
        // $(".right .add-tag").contentEditable = true;
        $(".right .add-tag span").focus();
        $(".right .add-tag span").on("keydown", function (e) {
            if ($(this).html() !== "" && e.keyCode == 13) {
                this.contentEditable = "false";
            }
        })
        // 为新添加的标签X绑定点击事件
        removeTag($(".add-tag-close"), classChanged);

    })
    // 添加已有专栏
    $(".checkbox").change(function (e) {
        if ($(this).prop("checked")) {
            var content = $('<button class="add-tag add-tag-class"><span>' + $(this).siblings("span").text() + '</span><i class="add-tag-close">x<i></button>');
            $(".right .new-class").before(content);
            classChanged();
        } else {
            $(".right .add-tag span").each(function() {
                if($(this).text() == $(e.target).siblings("span").text()) {
                    $(this).parent().remove();
                    classChanged();
                }
            })
        }
        removeTag($(".add-tag-close"), classChanged);
    })
    function classChanged() {
        if ($(".add-tag-class").length == 3) {
            $(".right .new-class").hide()
            $(".max-class-num").show()
            $(".max-class-num-mask").css('display', 'block')
        } else {
            $(".right .new-class").show();
            $(".max-class-num").hide();
            $(".max-class-num-mask").css('display', 'none')
        }
    }
    // $(".modal-bottom button").click(function() {
    //     var tags = [];
    //     var classify = [];
    //     $(".mark-selection .add-tag").each(function() {
    //         console.log($(this).children("span").text());
    //         tags.push($(this).children("span").text());
    //     }); //选择的标签
    //     $(".right .add-tag").each(function() {
    //         classify.push($(this).children("span").text());
    //     }); // 选择的分类
    //     var blogLabel = tags.join(',');
    //     var blogType = classify.join(',');
    //     console.log(tags.join(','));
    //     console.log(classify.join(','));
    // })


    $(function() {
        var editor = editormd("test-editor", {
            width: "100%",
            height: 600,
            path: "/editormd/lib/", //依赖lib文件夹路径
            saveHTMLToTextarea : true,
            // 图片上传
            imageUpload: true,
            imageFormats: ["png","bmp","jpg","jpeg","gif"],
            imageUploadURL: "/uploadEditorImage",
            onload : function() {
                console.log('onload', this);
            }
        });

        $("#input").click(function () {
            var tags = [];
            var classify = [];
            $(".mark-selection .add-tag").each(function() {
                console.log($(this).children("span").text());
                tags.push($(this).children("span").text());
            }); //选择的标签
            $(".right .add-tag").each(function() {
                classify.push($(this).children("span").text());
            }); // 选择的分类
            var typeString = tags.join(',');
            var labelString = classify.join(',');
            console.log(tags.join(','));
            console.log(classify.join(','));
            $.post({
                url: "/editormd",
                data: {
                    title: $("#title").val(),
                    // typeString: 1,// 此处需要异步获取类型id
                    // 存入子数据库的是markdown源码editor.getMarkdown()
                    editormd: editor.getMarkdown(),
                    typeString: typeString,
                    labelString: labelString
                },
                success: function () {
                    location.href="/";
                },
                error: function () {
                    console.log("发送失败");
                    alert("发送失败");
                }
            });
        })
    });
})