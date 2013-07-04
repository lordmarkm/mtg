$(function(){
	
	//whenever user clicks on 'reply' link, show the reply form
	$(document).on({
		click: function(){
			var $that = $(this);
			var commentId = $that.parent().data('comment-id');
			var $comment = $that.closest('.comment-container');
			var $replyContainer = $comment.find('.reply:first');
			
			var $form = $replyContainer.find('form');
			if($form.length > 0) {
				$form.find('textarea').focus();
			} else {
				$form = $('<form method="post">').attr('action', postUrls.reply + commentId).appendTo($replyContainer);
				
				var $usertext = $('<div class="usertext-edit">').appendTo($form);
				$('<textarea>').attr('name', 'text').appendTo($usertext).focus();
				$('<button class="submit btn btn-primary btn-mini">').text('save').appendTo($usertext);
				$('<button class="comment-cancel btn btn-mini ml5">').text('cancel').appendTo($usertext);
				
				//when the reply form is submitted successfully, show the new comment and remove the reply form
				$form.submit(function(){
					loadhere.instaloading();
					var $submitted = $(this);
					$.post($submitted.attr('action'), $submitted.serialize(), function(response) {
						switch(response.status) {
						case '200':
							$.get(postUrls.comment + response.comment.id + '?ajax', function(mav) {
								$submitted.parent().siblings('.replies').prepend(mav);
								$submitted.remove();
							}).complete(function(){
								loadhere.notloading();
							});
							break;
						default:
							footer.error('Error posting comment!');
						}
					}).error(function(){
						loadhere.notloading();
					});
					return false;
				});
			}
		}
	}, 'a.link-comment-reply');
	
	$(document).on({
		click: function(){
			var $that = $(this);
			var hidden = $that.closest('.comment-container')
				.find('.comment-body').toggleClass('hide').hasClass('hide');
			if(hidden) {
				$that.html('[+]');
			} else {
				$that.html('[-]');
			}
		}
	}, 'a.comment-expand');
	
	//remove form when user clicks the cancel button
	$(document).on({
		click: function(){
			$(this).closest('form').remove();
		}
	}, 'button.comment-cancel')
});