$(function () {
    // 需求：
    // 1. 点击发布文章 弹出发布模块
    // 2. 点击发布文章中的关闭按钮 关闭发布文章模块
    // 3. 添加文章标签
    // 获取元素
    var postMain = $(".csdn-post-article-modal"); // 整个发布模块

    // 1. 弹出发布模块
    var showPostMain = $(".show-post-article-btn");
    showPostMain.on("click", function () {
        $(postMain).fadeIn();
    })

    // 2 点击发布文章中的关闭按钮 关闭发布文章模块
    var hidePostMainBtn = $(".modal-publish button");
    hidePostMainBtn.on("click", function () {
        $(this).parents(".csdn-post-article-modal").fadeOut();
    })

    // 所有生成的文章标签列 
    var tagsList = $(".mark-section-title > div:first").children(".tags-list");
    console.log(tagsList);
    tagsListClickEvent(tagsList);
    // 3. 添加文章标签
    var addArticleTagBtn = $(".article-tag .mark-section-title .add-article-tag");
    addArticleTagBtn.on("click", function () {
        tagsListClickEvent(tagsList);
        // (1) 点击添加文章标签 显示和隐藏标签框
        $(".mark-section div.mark-section-box").show();

        // (2) 点击x号 隐藏标签框 
        $(".mark-section-box-selectTags .icon-close").on("click", function () {
            $(this).parents(".mark-section-box").hide();
        })

        // 获取添加文章标签的区域
        var domArticleTags = $(this).siblings("div").get(0); // jquery对象转化为dom对象
        // (3) 用户自己添加文章标签
        var articleTagText = $(".mark-section-box-search input").val();

        // 表单按键事件
        $(".mark-section-box-search input").on("keyup", function (e) {
            // (3.1)内容不空
            if ($(this).val() !== "") {
                // (3.2)按下的不是enter键 就取表单值
                if (e.keyCode !== 13) {
                    articleTagText = $(this).val();
                    return;
                } else {
                    tagsListClickEvent(tagsList);
                    // (3.3)如果是enter键就创建元素 获取值 并添加元素
                    var domTags = '<div class="mark-section-tag-box tags-list"><span>' + articleTagText + '</span><i class="icon icon-remove">x</i></div>'
                    tagsList = $(".mark-section-title > div:first").children(".tags-list");
                    // 文章到达5个就不能再添加了
                    if (tagsList.length >= 0 && tagsList.length <= 4) {
                        domArticleTags.insertAdjacentHTML("afterbegin", domTags);
                        tagsList = $(".mark-section-title > div:first").children(".tags-list");
                        $(".mark-section-box .mark-section-box-selectTags span em").text(5 - tagsList.length);
                        // console.log(tagsList);
                        // console.log("文章标签：" + tagsList.length);

                        // (3.4) 添加新的标签后 重新绑定x号点击事件
                        tagsListClickEvent(tagsList);
                    } else if (tagsList.length = 5) {
                        $(".mark-section-box .mark-section-box-selectTags span").html("<span style = 'color:red;'>最多只能添加5个标签！</span>");
                    }
                    tagsListClickEvent(tagsList);
                }
            }
            // 最后键入标签成功后 表单值清空
            $(this).val("");
            tagsListClickEvent(tagsList);
        })
    })

    // 判断文章标签的个数
    function tagsCount(tagsList) {
        if (tagsList.length >= 0 && tagsList.length <= 4) {
            $(".mark-section-box .mark-section-box-selectTags span em").text(5 - tagsList.length);
            tagsList = $(".mark-section-title > div:first").children(".tags-list");
        } else if (tagsList.length = 5) {
            // alert("只能添加5个标签哦");
            $(".mark-section-box .mark-section-box-selectTags span").html("<span style = 'color:red;'>最多只能添加5个标签！</span>");
        }
    }

    // (4) 删除文章标签
    // 遍历所有标签列 给x号绑定点击事件
    function tagsListClickEvent(tagsList) {
        tagsList = $(".mark-section-title > div:first").children(".tags-list");
        tagsCount(tagsList);
        tagsList.each(function (index, domEle) {
            $(domEle).on("click", ".icon-remove", function () {
                $(this).parent(".tags-list").remove();
                tagsList = $(".mark-section-title > div:first").children(".tags-list");
                // console.log(tagsList);
                console.log(tagsList.length);
                tagsCount(tagsList);
            })
        })
    }

    // 获取元素
    // 分类专栏部分 
    var classfyArea = $(".new-classfy .flex-box");
    // 获取分类专栏下面的选项
    var optionsList = $(".tag-options .tag-options-box>input");
    // 判断input被选中几个
    var inputLength = $(".tag-options .tag-options-box>input:checked").length;
    var newTagText = $(".flex-box .new-classfy-tag span").html();
    var newClassfyTag = '<div class="add-right new-classfy-tag tag-active"><span>' + newTagText + '</span><i class="icon icon-remove">x</i></div>';
    console.log(inputLength);


    // 分类专栏点击添加新专栏
    classfyArea.find(".add-new-classfy-tag").on("click", function () {
        // 先创建一个input 把用户输入的值赋给input 再把input的值给文本 同时删除input
        var inputValue;
        inputValue.on("keyup", function (e) {

        })
        var greatClassfyTag = '<div class="add-right new-classfy-tag tag-active"><input>' + inputValue + '</input><i class="icon icon-remove">x</i></div>'
    })


    // 专栏中的x
    var closeBtns = $(".new-classfy .icon-remove");

    function closeBtnClickEvent(closeBtns) {
        closeBtns = $(".new-classfy .icon-remove");
        inputLength = $(".tag-options .tag-options-box>input:checked").length;
        // 遍历绑定事件
        closeBtns.each(function (index, domEle) {
            $(domEle).on("click", function () {
                $(this).parent(".new-classfy-tag").remove();
                closeBtns = $(".new-classfy .icon-remove");
                // 当前关闭按钮的值
                var thisText = $(this).siblings("span").text();
                console.log(thisText + " this");
                // 关闭按钮对应的input
                var thisToInput = $(this).parents(".flex-box").siblings(".tag-options").find(".spanValue");
                // 删除对应的input
                console.log(closeBtns.length);
            })
        })
        inputLength = $(".tag-options .tag-options-box>input:checked").length;
    }
    closeBtnClickEvent(closeBtns);



    optionsList.each(function (index, domEle) {
        // $(domEle).eq(0);
        $(domEle).on("click", function () {
            console.log($(this).get(0).checked);
            // 表单被选中 添加选中的分类专栏
            if ($(this).get(0).checked) {
                // 小于3可以选择
                inputLength = $(".tag-options .tag-options-box>input:checked").length;
                if (inputLength <= 3) {
                    newTagText = $(this).siblings(".spanValue").html();
                    newClassfyTag = '<div class="add-right new-classfy-tag tag-active"><span>' + newTagText + '</span><i class="icon icon-remove">x</i></div>';
                    console.log(newTagText);
                    $(this).parents(".tag-options").siblings(".flex-box").prepend(newClassfyTag);
                    inputLength = $(".tag-options .tag-options-box>input:checked").length;
                    closeBtnClickEvent(closeBtns);
                } else {
                    alert("最多只能添加3个分类专栏！");
                    $(this).get(0).checked = false;
                }

            } else {
                // 删除对应的分类专栏
                // $(this).parents(".tag-options").siblings(".flex-box").before(newClassfyTag).remove();
            }
        })
    })

})