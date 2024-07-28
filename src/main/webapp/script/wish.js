function removeItem(event, wishSeq) {
    $.ajax({
        url: 'deleteWish.do',
        type: 'POST',
        data: { id: wishSeq },
        success: function(response) {
            if (response.success) {
                var item = event.target.closest('.movie-item');
                item.style.display = 'none';
            } else {
                alert('Failed to delete movie.');
            }
        },
        error: function() {
            alert('Error occurred while deleting movie.');
        }
    });
}