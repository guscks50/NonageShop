$.post("kindList.do", function(json){
    var dataLength = json.length;
    if ( dataLength >=1 ){
        var sCont = "<ul>";
        for ( i=0 ; i < dataLength ; i++){
            sCont += "<li><a href=kind.do?kind=" + json[i].no + ">" + json[i].name + "</a></li>";
        }
        sCont += "</ul>";
        if ($("#top_menu").has("ul").length < 1){    
            $("#top_menu").append(sCont); 
        }
        if ($("#sub_menu").has("ul").length < 1){
            $("#sub_menu").append(sCont);
        }
    }
});