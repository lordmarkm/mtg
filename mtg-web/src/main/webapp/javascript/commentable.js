$(function(){
	var commentable = $('#commentable');
	
	//whenever user clicks on 'reply' link, show the reply form
	commentable.on({
		click: function(){
			var $that = $(this);
			var commentId = $that.parent().data('comment-id');
			var $comment = $that.closest('.comment-container');
			var $replyContainer = $comment.find('.reply:first');
			
			var $form = $replyContainer.find('form');
			if($form.length > 0) {
				$form.find('textarea').focus();
			} else {
				$form = $('<form class="form-comment" method="post">').attr('action', postUrls.reply + commentId).appendTo($replyContainer);
				
				var $usertext = $('<div class="usertext-edit">').appendTo($form);
				$('<textarea placeholder="Reply to this comment">').attr('name', 'text').appendTo($usertext).focus();
				$('<button class="comment-submit btn btn-primary btn-mini">').text('save').appendTo($usertext);
				$('<button class="comment-cancel btn btn-mini ml5">').text('cancel').appendTo($usertext);
			}
		}
	}, 'a.link-comment-reply');
	
	//whenever user clicks on 'delete' link, ask for confirmation, and then delete
	commentable.on({
		click: function(){
			var $that = $(this);
			var $confirm = $('<span class="alert alert-danger">').text('Are you sure? ').insertAfter($that);
			$('<a class="link-confirm-delete" href="javascript:;">').text('Yes').appendTo($confirm);
			$confirm.append(' / ');
			$('<a class="link-cancel-delete" href="javascript:;">').text('No').appendTo($confirm).click(function(){
				$that.show();
				$confirm.remove();
			});
			$that.hide();
		}
	}, 'a.link-comment-delete,a.link-post-delete');
	
	//continuation of the above; on confirm delete, delete the post
	commentable.on({
		click: function(){
			var $that = $(this);
			var id = $that.closest('.post-controls').data('post-id');
			if(!id) return;
			$.post(postUrls.deletePost + id, function(response) {
				switch(response.status) {
				case '200':
					$that.closest('.post-container').fadeOut(function(){
						$(this).remove();
					});
					break;
				default:
					footer.error('Error deleting post!');
				}
			});
		}
	}, '.link-confirm-delete');
	
	//continuation of the above; on confirm delete, delete the comment
	commentable.on({
		click: function(){
			var $that = $(this);
			var id = $that.closest('.comment-controls').data('comment-id');
			if(!id) return;
			$.post(postUrls.deleteComment + id, function(response) {
				switch(response.status) {
				case '200':
					$that.closest('.comment-container').fadeOut(function(){
						$(this).remove();
					});
					break;
				default:
					footer.error('Error deleting comment!');
				}
			});
		}
	}, 'a.link-confirm-delete');
	
	commentable.on({
		submit: function(){
			//when the reply form is submitted successfully, show the new comment and remove the reply form
			var $form = $(this);
			
			$form.find('.comment-submit').attr('disabled', 'disabled').addClass('disabled');
			
			loadhere.instaloading();
			var $submitted = $(this);
			$.post($submitted.attr('action'), $submitted.serialize(), function(response) {
				switch(response.status) {
				case '200':
					$.get(postUrls.comment + response.comment.id + '?ajax', function(mav) {
						var $reps = $submitted.parent().siblings('.replies');
						if($reps.length === 0) {
							$reps = $('#post-replies');
						}
						$reps.prepend(mav);
						
						//clear text if main form. else, remove form
						if($submitted.attr('id') === 'form-on-post') {
							$submitted.find('textarea').val('');
						} else {
							$submitted.remove();
						}
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
		}
	}, '.form-comment')
	
	commentable.on({
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
	commentable.on({
		click: function(){
			$(this).closest('form').remove();
		}
	}, 'button.comment-cancel')
});