// ckeditor.js
ClassicEditor
    .create(document.querySelector('#content'))
    .then(editor => {
        console.log('Default CKEditor:', editor);
    })
    .catch(error => {
        console.error(error);
    });

// Remove a plugin from the default setup.
ClassicEditor
    .create( document.querySelector( '#guestcontent' ), {
        removePlugins: [ 'Heading' ],
        toolbar: [ 'bold', 'italic', 'bulletedList', 'numberedList', 'blockQuote' , 'link' ]
    } )
    .catch( error => {
        console.log( error );
    } );
