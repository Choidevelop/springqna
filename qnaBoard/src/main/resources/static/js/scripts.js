// $(".answer-write input[type=submit]").click(addAnswer);
$(document).on('click','.answer-write input[type=submit]', addAnswer);

function addAnswer(e) {
  e.preventDefault();
  console.log("click me");

  var queryString = $(".answer-write").serialize();
  console.log("query : " + queryString);

  var url = $(".answer-write").attr("action");
  console.log("url : " + url);

  $.ajax({
    type: 'post',
    url: url,
    data: queryString,
    dataType: 'json',
    error: onError,
    success: onSuccess
  });
}

function onError() {

}
function onSuccess(data, status) {
  console.log(data);
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(data.writer.userId, data.createdTimedAt, data.contents, data.question.id, data.id, data.question.countOfAnswer);
  $(".qna-comment-count").html("<strong>"+data.question.countOfAnswer+"</strong>개의 의견");
  $(".qna-comment-slipp-articles").prepend(template);
  $(".answer-write textarea").val("");
}

// $(".link-delete-article").click(deleteAnswer);
$(document).on('click','.link-delete-article', deleteAnswer);

function deleteAnswer(e) {
  e.preventDefault();

  var deleteBtn = $(this);
  var url = deleteBtn.attr("href");
  console.log("url : " + url);
  $.ajax({
    type: 'delete',
    url: url,
    dataType: 'json',
    error: function (xha, status) {
      console.log("error");
    },
    success: function (data, status) {
      console.log(data);

      if(data.vaild){
        deleteBtn.closest("article").remove();
        $(".qna-comment-count").html("<strong>"+data.question.countOfAnswer+"</strong>개의 의견");
      }
      else{
        alert(data.errorMessage);
      }
      }
  });
}

String.prototype.format = function () {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function (match, number) {
    return typeof args[number] != 'undefined'
      ? args[number]
      : match
      ;
  });
};