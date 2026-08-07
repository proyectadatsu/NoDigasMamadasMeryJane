// 1. DARK MODE TOGGLE & LOCALSTORAGE PERSISTENCE
function applyThemePreference() {
    const theme = localStorage.getItem('probuild-theme') || 'light';
    document.documentElement.setAttribute('data-bs-theme', theme);
    updateThemeUI(theme);
}

function toggleDarkMode() {
    const current = document.documentElement.getAttribute('data-bs-theme');
    const next = current === 'dark' ? 'light' : 'dark';
    document.documentElement.setAttribute('data-bs-theme', next);
    localStorage.setItem('probuild-theme', next);
    updateThemeUI(next);
}

function updateThemeUI(theme) {
    const icon = document.getElementById('themeIcon');
    const text = document.getElementById('themeText');

    if (!icon || !text) return;

    if (theme === 'dark') {
        icon.className = 'bi bi-sun-fill text-warning';
        text.textContent = 'Modo Claro';
    } else {
        icon.className = 'bi bi-moon-stars-fill';
        text.textContent = 'Modo Oscuro';
    }
}

// 2. PREPARAR MODAL DE EDICION CON DATOS DEL PRODUCTO
function prepararEdicion(id, nombre, marca, categoria, precio, stock, descripcion) {
    document.getElementById('formEditarProducto').action = '/productos/actualizar/' + id;
    document.getElementById('edit_id').value = id;
    document.getElementById('edit_nombre').value = nombre;
    document.getElementById('edit_marca').value = marca;
    document.getElementById('edit_categoria').value = categoria;
    document.getElementById('edit_precio').value = precio;
    document.getElementById('edit_stock').value = stock;
    document.getElementById('edit_descripcion').value = descripcion;
}

// 3. LIVE REAL-TIME SEARCH & CATEGORY FILTER
function executeLiveFilter() {
    const searchInput = document.getElementById('searchInput');
    const categorySelect = document.getElementById('categorySelect');

    if (!searchInput || !categorySelect) return;

    const query = searchInput.value.toLowerCase().trim();
    const selectedCat = categorySelect.value.toLowerCase().trim();

    // Filter Product Cards
    document.querySelectorAll('.product-card-item').forEach(card => {
        const text = card.textContent.toLowerCase();
        const category = (card.getAttribute('data-category') || '').toLowerCase();

        const matchQuery = text.includes(query);
        const matchCategory = !selectedCat || category === selectedCat;

        card.style.display = (matchQuery && matchCategory) ? '' : 'none';
    });

    // Filter Table Rows
    document.querySelectorAll('#productosTable tbody tr.product-table-row').forEach(row => {
        const text = row.textContent.toLowerCase();
        const category = (row.getAttribute('data-category') || '').toLowerCase();

        const matchQuery = text.includes(query);
        const matchCategory = !selectedCat || category === selectedCat;

        row.style.display = (matchQuery && matchCategory) ? '' : 'none';
    });
}

// 4. VIEW MODE SWITCH (GRID CARDS VS TABLE)
function switchModeView(mode) {
    const gridView = document.getElementById('gridView');
    const tableView = document.getElementById('tableView');
    const btnGrid = document.getElementById('btnGridView');
    const btnTable = document.getElementById('btnTableView');

    if (!gridView || !tableView) return;

    if (mode === 'table') {
        gridView.classList.add('d-none');
        tableView.classList.remove('d-none');
        btnTable.classList.add('active');
        btnGrid.classList.remove('active');
    } else {
        tableView.classList.add('d-none');
        gridView.classList.remove('d-none');
        btnGrid.classList.add('active');
        btnTable.classList.remove('active');
    }
}

// Initialize on DOM Load
document.addEventListener('DOMContentLoaded', () => {
    applyThemePreference();

    const searchInput = document.getElementById('searchInput');
    const categorySelect = document.getElementById('categorySelect');

    searchInput?.addEventListener('keyup', executeLiveFilter);
    categorySelect?.addEventListener('change', executeLiveFilter);
});
