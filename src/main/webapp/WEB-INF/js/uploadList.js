$(function(){
    $.ajax({
        type: 'post'
        , url: '/user/getUploadList'
        , dataType: 'json'
        , success: function(data){
            console.log(JSON.stringify(data));
            $.each(data, function (index, items){
                /*$('<tr/>').append($('<td/>', {
                            align: 'center'
                            , text: items.seq
                            })).append($('<td/>', {
                            align: 'center'
                            , text: items.imageOriginalName
                                }).append($('<img/>', {
                                    src: '../storage/' + items.imageOriginalName       // 가상 폴더의 위치를 잡아준다.
                                    , style: 'width: 50px; height: 50px;'
                                })))
                            .append($('<td/>', {
                            align: 'center'
                            , text: items.imageName
                })).appendTo($('#uploadListTable'));*/

                // 이미지는 NCP Storage에서 가져온다.
                var result = `<tr>`
                    + `<td align="center">`
                        + `<input type="checkbox" id="check" name="check" value="` + items.seq + `">`
                        + `<label for="seq"> `
                        + items.seq
                        + `</lable>`
                    +`</td>`
                    + `<td align="center">`
                    + `<a href="/user/uploadUpdateForm?seq=`+ items.seq +`"><img src="https://kr.object.ncloudstorage.com/bitcamp-6th-bucket-94/storage/`+items.imageFileName+`" style="width: 50px"></a>`
                    +`</td>`
                    + `<td align="center">`+items.imageName+`</td>`
                    + `</tr>`
                $('#uploadListTable').append(result);

            });
            //전체 선택/해제
            $('#all').click(function (){
                if($('this').prop('checked')){
                    $('input[name="check"]').prop('checked', "true")
                }else{
                    $('input[name="check"]').prop('checked', "false")
                }
            });
            // 전체 선택한 후, 다른 item값을 해제하면 all 는 자동으로 해제로 변한다.
            $('input[name="check"]').click(function (){
                $('#all').prop('checked', ($('input[name="check"]:checked').length === $('input[name="check"]').length));
            })

        }
        , error: function(e){
            console.log(e);
        }
    });
})