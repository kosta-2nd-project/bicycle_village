function checkValid(){
    var f = window.document.writeForm;

    if ( f.board_name.value == "" ) {
        alert( "제목을 입력해 주세요." );
        f.board_name.focus();
        return false;
    }

    if ( f.board_content.value == "" ) {
        alert( "상품 설명을 입력해 주세요." );
        f.board_content.focus();
        return false;
    }


    /* 		if (f.goods_price.value == "") {
                alert("가격을 입력해 주세요.");
                f.goods_price.focus();
                return false;
            } */

    /* 		if (f.image_name == "") {
                alert("이미지를 1개 이상 입력해 주세요.");
                f.image_name.focus();
                return false;
            } */
    return true;

}