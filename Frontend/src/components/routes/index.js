import DedaultLayout from '~/components/Layouts/DefaultLayout';
import AdminLayout from '../Layouts/AdminLayout';
import Home from '~/components/Pages/Home';
import Search from '~/components/Pages/Search';
import Login from '../Pages/Login';
import Register from '../Pages/Register';
import DetailProduct from '../Pages/DetailProduct';
import Profile from '../Pages/Profile';
import Cart from '../Pages/Cart';
import AddBook from '../Pages/Admin/AddBook';
import BookManager from '../Pages/Admin/ManageBook';
import Author from '../Pages/Author';
import UserManager from '../Pages/Admin/ManageUser';
import UpdateBook from '../Pages/Admin/UpdateBook';
import CategoryManage from '../Pages/Admin/CategoryManage';

const routes = [
    { path: '/', element: Home, layout: DedaultLayout },
    { path: '/home', element: Home, layout: DedaultLayout },
    { path: '/search', element: Search, layout: DedaultLayout },
    { path: '/login', element: Login, layout: DedaultLayout },
    { path: '/register', element: Register, layout: DedaultLayout },
    { path: '/book/:id', element: DetailProduct, layout: DedaultLayout },
    { path: '/profile', element: Profile, layout: DedaultLayout },
    { path: '/cart', element: Cart, layout: DedaultLayout },
    { path: '/author/:id', element: Author, layout: DedaultLayout },
    { path: '/admin', element: BookManager, page: '1', layout: AdminLayout },
    { path: '/admin', element: BookManager, page: '1', layout: AdminLayout },
    { path: '/admin/user', element: UserManager, page: '2', layout: AdminLayout },
    { path: '/admin/addbook', element: AddBook, page: '12', layout: AdminLayout },
    { path: '/admin/UpdateBook/:id', element: UpdateBook, page: '1', layout: AdminLayout },
    { path: '/admin/category', element: CategoryManage, page: '3', layout: AdminLayout },
];

export { routes };
