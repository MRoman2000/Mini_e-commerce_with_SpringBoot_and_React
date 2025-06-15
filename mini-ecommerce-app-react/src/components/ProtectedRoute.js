import { Navigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';

const ProtectedRoute = ({ children, requiredRole }) => {
   const token = localStorage.getItem('accessToken');
  
  if (!token) {
    return <Navigate to="/login" />;
  }

  const decoded = jwtDecode(token);

  const userRole = decoded.roles; 
  if (requiredRole && userRole !== requiredRole) {
    return <Navigate to="/" />; 
  }

  return children;
}
export default ProtectedRoute;
