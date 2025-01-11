import { Outlet } from "react-router-dom";
import Topbar from "@/components/layouts/Topbar";

const Layout = () => {
  return (
    <div className="min-h-screen flex flex-col">
      <header className="sticky top-0 z-50 shadow-sm">
        <Topbar />
      </header>

      <main className="flex-grow container mx-auto px-4 py-6 md:px-6 lg:px-8">
        <div className="max-w-7xl mx-auto">
          <Outlet />
        </div>
      </main>
    </div>
  );
};

export default Layout;