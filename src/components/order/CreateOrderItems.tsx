import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";
import { Table, TableBody, TableHead, TableHeader, TableRow } from "../ui/table";
import { useContext } from "react";
import { CartContext } from "@/contexts/CartContext";
import OrderItem from "./OrderItem";

const CreateOrderItems = () => {
  const {cart} = useContext(CartContext);

  return (
    <Card className="shadow-sm">
      <div className="max-h-96 overflow-y-auto">
        <CardHeader className="pb-4">
          <CardTitle className="text-lg font-medium">Order Items</CardTitle>
        </CardHeader>
        <CardContent>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead className="text-left">Product</TableHead>
                <TableHead className="text-left">Title</TableHead>
                <TableHead className="text-center">Quantity</TableHead>
                <TableHead className="text-right">Subtotal</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {cart?.items.map((item) => (
                <OrderItem item={item} />
              ))}
            </TableBody>
          </Table>
        </CardContent>
      </div>
    </Card>
  );
};

export default CreateOrderItems;