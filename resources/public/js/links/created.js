document
    .querySelectorAll('.link-copy-button')
    .forEach(copyButton => {
        copyButton.addEventListener('click', () => {
            const { link } = copyButton.dataset;
            navigator.clipboard.writeText(link);
            copyButton.textContent = 'Copied!';
        });
    });

document
    .querySelectorAll('.link-share-button')
    .forEach(shareButton => {
        shareButton.addEventListener('click', () => {
            const { link } = shareButton.dataset;
            navigator.share({
                // Use 'text' instead of 'url' to correct showing emoji link.
                text: link,
            });
        });
    });

if (navigator.share) {
    document
        .querySelectorAll('.link-share-button')
        .forEach(shareButton => shareButton.classList.remove('hide'));
}
