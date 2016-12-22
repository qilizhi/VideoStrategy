var comm = {
	initDel : function() {
		var $this = this;
		$('body').find('.list-del').on('click', function(e) {
			// console.log(e);
			var url = $(this).attr("href");
			e.preventDefault();
			$this.confirm("提示", "此操作不可逆,您确定要继续进行删除操作吗？", function() {
				// console.log("删除"+url);
				$.post(url, function(result) {
					// console.log(result);
					if (result.code == "success") {
						// $this.alert("提示","删除操作成功");
						setTimeout(function() {
							window.location.href = window.location.href;
						}, 1000);
					} else if (result.code == "fail") {
						$this.alert("提示", "抱歉,删除操作失败,请重试!");
					}
				});
			});

		});

	},
	initAdd : function() {
		// var $this = this;
		$('body').find('.list-add').on('click', function(e) {
			var url = $(this).attr("href");
			window.location.href=url;
		});

	},
	initEdit:function(){
		//alert("edit");
		$('body').find('.list-edit').on('click', function(e) {
			var url = $(this).attr("href");
			window.location.href=url;
		});
	},
	initAllCheck:function(){
		console.log($("body").find("thead tr th input"));
		$("body").find("thead tr th input").on("click",function(e){
			if(e.currentTarget['checked']){
				$("body").find("tbody tr td input").each(function(index,object){
					$(object)['0'].checked=true;
				});
			}else{
				$("body").find("tbody tr td input").each(function(index,object){
					$(object)['0'].checked=false;
				
				});
			}
		});
	}
	,
	confirm : function(title, content, func) {
		var $modal = $("#basic-modals");
		// console.log($modal);
		func = func || function(e) {
			$modal.modal("close");
		};
		$modal.modal("open");
		$modal.find(".am-modal-hd").html(title || "提示");
		$modal.find(".am-modal-bd").html(content || "是否继续此操作?");
		$modal.find(".close").off("click").on("click", function(e) {
			$modal.modal("close");
			func();
		});
	},
	alert : function(title, content) {
		// alert("conetent");
		var $modal = $("#base-alert");
		$modal.modal("open");
		$modal.find(".am-modal-hd").html(title || "提示");
		$modal.find(".am-modal-bd").html(content);
		$modal.find(".am-modal-btn").off("click").on("click", function(e) {
			$modal.modal("close");
		});
	},
	showMsg : function(content) {
		// alert("conetent");
		var $modal = $("#base-alert");
		$modal.modal("open");
		$modal.find(".am-modal-hd").html("提示");
		$modal.find(".am-modal-bd").html(content);
		$modal.find(".am-modal-btn").off("click").on("click", function(e) {
			$modal.modal("close");
		});
	},
	
	init : function() {
		// this.initTableDetail();
		this.initDel();
		this.initAdd();
		this.initEdit();
		this.initAllCheck();
		// this.initSort();
		// this.initAjaxForm();
	}

};
comm.init();
// 设置jQuery Ajax全局的参数
$.ajaxSetup({
	beforeSend : function(xhr) {
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		xhr.setRequestHeader(header, token);
	},
	error : function(jqXHR, textStatus, errorThrown) {
		// console.log(textStatus);
		// console.log(jqXHR);
		switch (jqXHR.status) {
		case (500):
			comm.showMsg("服务器系统内部错误");
			break;
		case (401):
			comm.showMsg("未登录");
			break;
		case (403):
			// alert("无权限执行此操作");
			comm.showMsg("无权限执行此操作");
			break;
		case (408):
			comm.showMsg("请求超时");
			break;
		default:
			comm.showMsg("未知错误");
		}
	}
});
