$(function (){
    $.ajax({
        type: 'post'
        , url: '/user/getUserList'
        , data: {'pg' : $('#pg').val()}
        , dataType: 'json'
        , success: function (data){
            console.log(JSON.stringify(data));  // 콘솔로 확인하려고

            $.each(data.list, function (index, items){
                /*$('<tr/>')
                    .append($('<td/>', {align: 'center', text: items.name}))
                    .append($('<td/>', {align: 'center', text: items.id}))
                    .append($('<td/>', {align: 'center', text: items.pwd}))
                    .appendTo($('#userListTable'))*/

                var result = `<tr>`
                            + `<td align="center">`+ items.name +`</td>`
                            + `<td align="center">`+ `<a href="#" class="idA">`+ items.id + '</a>'+`</td>`
                            + `<td align="center">`+ items.pwd +`</td>`
                $('#userListTable').append(result);
            });

            // 페이징 처리
            $('#userPagingDiv').html(data.userPaging.pagingHTML);

            //아이디를 클릭했을 때
            $('.idA').click(function (){
                // alert('name=' + $(this).parent().prev().text());
                location.href = '/Chapter06_Web/user/updateForm?id=' + $(this).text() + '&pg=' + $('#pg').val();
            })
        }
        , error: function (e) {
            console.log(e);
        }
    })
})