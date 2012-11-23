/**
 * Check if the value in parameter is null or blank.
 * Return true if the field doesn't blank, else false.
 */
function notNullOrBlank(value) {
	if(value!=null && $.trim(value)!='')
		return true;
	else return false;
}

/**
  * Demande confirmation a l'utilisateur avant de supprimer un element
  * @param deleteButton : le bouton sur lequel l'utilisateur a cliqué
  * @param form : le formulaire à envoyer s'il y a confirmation
  */
function confirmBeforeDelete(deleteButton, form) {
	$(document).delegate('#'+$(deleteButton).attr("id"), 'click', function() {
		$(this).simpledialog({
			'mode' : 'bool',
			'prompt' : 'Confirmer la suppression?',
			'cleanOnClose': true,
			'useModal' : true,
			'buttons' : {
				'OK': {
					click: function () { 
						$(form).submit();
						return false;
					}
				},
				'Cancel': {
					click: function () { 
						return true;
					},
					icon: "delete",
					theme: "a"
				}
			}
		});
	});
}

/**
 * Demande confirmation a l'utilisateur avant de supprimer un element
 * 
 * @param deleteButton : le bouton sur lequel l'utilisateur a cliqué
 * @param action : l'action à exécuter s'il y a confirmation
 */
function confirmBeforeDeleteWithoutForm(deleteButton, action) {
	$(document).delegate('#'+$(deleteButton).attr("id"), 'click', function() {
		$(this).simpledialog({
			'mode' : 'bool',
			'prompt' : 'Confirmer la suppression?',
			'cleanOnClose': true,
			'useModal' : true,
			'buttons' : {
				'OK': {
					click: function () { 
						location.href=action;
						return false;
					}
				},
				'Cancel': {
					click: function () { 
						return true;
					},
					icon: "delete",
					theme: "a"
				}
			}
		});
	});
}

/**
  * Print error message in the current page
  */
function printError(errorMessage) {
	var offset = ($('#error').offset().top)-50;
	if($(window).scrollTop()>offset)
		$.mobile.silentScroll(offset);
	
	$('#error').html('');
	$('#error').append("<p>"+errorMessage+"</p>").trigger("create");
}

