$(function(){
    $('#cart_delete').on("click", function(){
        var count =  $("input[type='checkbox']:checked").length;
		if (count == 0) {
			alert("삭제할 항목을 선택해 주세요.");
		} else {
			$("#cartform").attr("action", "cartDelete.do");
			$('#cartform').submit();
		}
	});
    
    $('#continue').on("click", function(){
        location.href='index.do';
    });
    
    $('#gocart').on("click", function(){
		if ($('#quantity').val().length == 0) {
			alert("수량을 입력하여 주세요.");
			$('#quantity').focus();
		} else {
			$("#cartform").attr("action", "cartInsert.do");
			$('#cartform').submit();
		}
    });
    
    $('#goorder').on("click", function(){
        go_order();
    });
    
    $('#go_order_insert').on("click", function(){
        $("#cartform").attr("action", "orderInsert.do");
    	$('#cartform').submit();
    });
});


function go_order_delete() {
  var count = 0;

  if (document.formm.oseq.length == undefined) {
    if (document.formm.oseq.checked == true) {
      count++;
    }
  }

  for ( var i = 0; i < document.formm.oseq.length; i++) {
    if (document.formm.oseq[i].checked == true) {
      count++;
    }
  }
  if (count == 0) {
    alert("삭제할 항목을 선택해 주세요.");

  } else {
    document.formm.action = "NonageServlet?command=order_delete";
    document.formm.submit();
  }
}

function go_order() {
  document.formm.action = "NonageServlet?command=mypage";
  document.formm.submit();
}