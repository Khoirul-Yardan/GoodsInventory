const API_BASE = 'http://localhost:8000/api';

// Show/hide sections
function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => section.style.display = 'none');
    document.getElementById(sectionId).style.display = 'block';
    
    if (sectionId === 'dashboard') {
        loadDashboard();
    } else if (sectionId === 'goods') {
        loadGoods();
    } else if (sectionId === 'staff') {
        loadStaff();
    } else if (sectionId === 'input') {
        loadInputs();
        loadGoodsForSelect('inputGoodsId');
    } else if (sectionId === 'output') {
        loadOutputs();
        loadGoodsForSelect('outputGoodsId');
    } else if (sectionId === 'category') {
        loadCategories();
    }
}

// Dashboard functions
async function loadDashboard() {
    try {
        const [goods, inputs, outputs, staff] = await Promise.all([
            fetch(`${API_BASE}/Goods`).then(r => r.json()),
            fetch(`${API_BASE}/GoodsIn`).then(r => r.json()),
            fetch(`${API_BASE}/GoodsOut`).then(r => r.json()),
            fetch(`${API_BASE}/Staff`).then(r => r.json())
        ]);
        
        document.getElementById('totalGoods').textContent = goods.length;
        document.getElementById('totalInput').textContent = inputs.length;
        document.getElementById('totalOutput').textContent = outputs.length;
        document.getElementById('totalStaff').textContent = staff.length;
    } catch (error) {
        console.error('Error loading dashboard:', error);
    }
}

// Goods functions
function showAddGoodsForm() {
    document.getElementById('addGoodsForm').style.display = 'block';
}

function hideAddGoodsForm() {
    document.getElementById('addGoodsForm').style.display = 'none';
    document.getElementById('goodsForm').reset();
}

async function loadGoods() {
    try {
        const response = await fetch(`${API_BASE}/Goods`);
        const goods = await response.json();
        
        const tbody = document.getElementById('goodsTableBody');
        tbody.innerHTML = '';
        
        goods.forEach(good => {
            tbody.innerHTML += `
                <tr>
                    <td>${good.id}</td>
                    <td>${good.name}</td>
                    <td>${good.category}</td>
                    <td>${good.quantity}</td>
                    <td>$${good.price}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="deleteGoods(${good.id})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        console.error('Error loading goods:', error);
    }
}

async function loadGoodsForSelect(selectId) {
    try {
        const response = await fetch(`${API_BASE}/Goods`);
        const goods = await response.json();
        
        const select = document.getElementById(selectId);
        select.innerHTML = '<option value="">Select Goods</option>';
        
        goods.forEach(good => {
            select.innerHTML += `<option value="${good.id}">${good.name}</option>`;
        });
    } catch (error) {
        console.error('Error loading goods for select:', error);
    }
}

// Staff functions
function showAddStaffForm() {
    document.getElementById('addStaffForm').style.display = 'block';
}

function hideAddStaffForm() {
    document.getElementById('addStaffForm').style.display = 'none';
    document.getElementById('staffForm').reset();
}

async function loadStaff() {
    try {
        const response = await fetch(`${API_BASE}/Staff`);
        const staff = await response.json();
        
        const tbody = document.getElementById('staffTableBody');
        tbody.innerHTML = '';
        
        staff.forEach(member => {
            tbody.innerHTML += `
                <tr>
                    <td>${member.id}</td>
                    <td>${member.name}</td>
                    <td>${member.email}</td>
                    <td>${member.position}</td>
                    <td>${member.phone}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="deleteStaff(${member.id})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        console.error('Error loading staff:', error);
    }
}

// Input functions
function showAddInputForm() {
    document.getElementById('addInputForm').style.display = 'block';
}

function hideAddInputForm() {
    document.getElementById('addInputForm').style.display = 'none';
    document.getElementById('inputForm').reset();
}

async function loadInputs() {
    try {
        const response = await fetch(`${API_BASE}/GoodsIn`);
        const inputs = await response.json();
        
        const tbody = document.getElementById('inputTableBody');
        tbody.innerHTML = '';
        
        inputs.forEach(input => {
            tbody.innerHTML += `
                <tr>
                    <td>${input.id}</td>
                    <td>${input.goods ? input.goods.name : 'N/A'}</td>
                    <td>${input.quantity}</td>
                    <td>${input.supplier}</td>
                    <td>${new Date(input.inputDate).toLocaleDateString()}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="deleteInput(${input.id})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        console.error('Error loading inputs:', error);
    }
}

// Output functions
function showAddOutputForm() {
    document.getElementById('addOutputForm').style.display = 'block';
}

function hideAddOutputForm() {
    document.getElementById('addOutputForm').style.display = 'none';
    document.getElementById('outputForm').reset();
}

async function loadOutputs() {
    try {
        const response = await fetch(`${API_BASE}/GoodsOut`);
        const outputs = await response.json();
        
        const tbody = document.getElementById('outputTableBody');
        tbody.innerHTML = '';
        
        outputs.forEach(output => {
            tbody.innerHTML += `
                <tr>
                    <td>${output.id}</td>
                    <td>${output.goods ? output.goods.name : 'N/A'}</td>
                    <td>${output.quantity}</td>
                    <td>${output.customer}</td>
                    <td>${new Date(output.outputDate).toLocaleDateString()}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="deleteOutput(${output.id})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        console.error('Error loading outputs:', error);
    }
}

// Category functions
function showAddCategoryForm() {
    document.getElementById('addCategoryForm').style.display = 'block';
}

function hideAddCategoryForm() {
    document.getElementById('addCategoryForm').style.display = 'none';
    document.getElementById('categoryForm').reset();
}

async function loadCategories() {
    try {
        const response = await fetch(`${API_BASE}/Category`);
        const categories = await response.json();
        
        const tbody = document.getElementById('categoryTableBody');
        tbody.innerHTML = '';
        
        categories.forEach(category => {
            tbody.innerHTML += `
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>${category.description}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="deleteCategory(${category.id})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        console.error('Error loading categories:', error);
    }
}

// Form submissions
document.getElementById('goodsForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const goods = {
        name: document.getElementById('goodsName').value,
        description: document.getElementById('goodsDescription').value,
        quantity: parseInt(document.getElementById('goodsQuantity').value),
        price: parseFloat(document.getElementById('goodsPrice').value),
        category: document.getElementById('goodsCategory').value
    };
    
    try {
        await fetch(`${API_BASE}/Goods`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(goods)
        });
        
        hideAddGoodsForm();
        loadGoods();
        alert('Goods added successfully!');
    } catch (error) {
        console.error('Error adding goods:', error);
        alert('Error adding goods!');
    }
});

document.getElementById('staffForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const staff = {
        name: document.getElementById('staffName').value,
        email: document.getElementById('staffEmail').value,
        position: document.getElementById('staffPosition').value,
        phone: document.getElementById('staffPhone').value
    };
    
    try {
        await fetch(`${API_BASE}/Staff`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(staff)
        });
        
        hideAddStaffForm();
        loadStaff();
        alert('Staff added successfully!');
    } catch (error) {
        console.error('Error adding staff:', error);
        alert('Error adding staff!');
    }
});

document.getElementById('inputForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const input = {
        goodsId: parseInt(document.getElementById('inputGoodsId').value),
        quantity: parseInt(document.getElementById('inputQuantity').value),
        supplier: document.getElementById('inputSupplier').value,
        notes: document.getElementById('inputNotes').value
    };
    
    try {
        await fetch(`${API_BASE}/GoodsIn`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(input)
        });
        
        hideAddInputForm();
        loadInputs();
        loadGoods(); // Refresh goods to show updated quantities
        alert('Input added successfully!');
    } catch (error) {
        console.error('Error adding input:', error);
        alert('Error adding input!');
    }
});

document.getElementById('outputForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const output = {
        goodsId: parseInt(document.getElementById('outputGoodsId').value),
        quantity: parseInt(document.getElementById('outputQuantity').value),
        customer: document.getElementById('outputCustomer').value,
        notes: document.getElementById('outputNotes').value
    };
    
    try {
        await fetch(`${API_BASE}/GoodsOut`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(output)
        });
        
        hideAddOutputForm();
        loadOutputs();
        loadGoods(); // Refresh goods to show updated quantities
        alert('Output added successfully!');
    } catch (error) {
        console.error('Error adding output:', error);
        alert('Error adding output!');
    }
});

document.getElementById('categoryForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const category = {
        name: document.getElementById('categoryName').value,
        description: document.getElementById('categoryDescription').value
    };
    
    try {
        await fetch(`${API_BASE}/Category`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(category)
        });
        
        hideAddCategoryForm();
        loadCategories();
        alert('Category added successfully!');
    } catch (error) {
        console.error('Error adding category:', error);
        alert('Error adding category!');
    }
});

// Delete functions
async function deleteGoods(id) {
    if (confirm('Are you sure you want to delete this goods?')) {
        try {
            await fetch(`${API_BASE}/Goods/${id}`, {method: 'DELETE'});
            loadGoods();
            alert('Goods deleted successfully!');
        } catch (error) {
            console.error('Error deleting goods:', error);
            alert('Error deleting goods!');
        }
    }
}

async function deleteStaff(id) {
    if (confirm('Are you sure you want to delete this staff member?')) {
        try {
            await fetch(`${API_BASE}/Staff/${id}`, {method: 'DELETE'});
            loadStaff();
            alert('Staff deleted successfully!');
        } catch (error) {
            console.error('Error deleting staff:', error);
            alert('Error deleting staff!');
        }
    }
}

async function deleteInput(id) {
    if (confirm('Are you sure you want to delete this input record?')) {
        try {
            await fetch(`${API_BASE}/GoodsIn/${id}`, {method: 'DELETE'});
            loadInputs();
            alert('Input deleted successfully!');
        } catch (error) {
            console.error('Error deleting input:', error);
            alert('Error deleting input!');
        }
    }
}

async function deleteOutput(id) {
    if (confirm('Are you sure you want to delete this output record?')) {
        try {
            await fetch(`${API_BASE}/GoodsOut/${id}`, {method: 'DELETE'});
            loadOutputs();
            alert('Output deleted successfully!');
        } catch (error) {
            console.error('Error deleting output:', error);
            alert('Error deleting output!');
        }
    }
}

async function deleteCategory(id) {
    if (confirm('Are you sure you want to delete this category?')) {
        try {
            await fetch(`${API_BASE}/Category/${id}`, {method: 'DELETE'});
            loadCategories();
            alert('Category deleted successfully!');
        } catch (error) {
            console.error('Error deleting category:', error);
            alert('Error deleting category!');
        }
    }
}

// Initialize dashboard on page load
document.addEventListener('DOMContentLoaded', () => {
    showSection('dashboard');
});