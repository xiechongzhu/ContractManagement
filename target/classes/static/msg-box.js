function messagebox(msgbox_title, msgbox_content, msgbox_type,
                    msgbox_afterclose_fun) {
    $.msgBox({
        title: msgbox_title,
        content: msgbox_content,
        type: msgbox_type,
        afterClose: msgbox_afterclose_fun
    });
}