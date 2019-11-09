package com.example.advt.controller;

        import com.example.advt.dao.SubCategoryDAO;
        import com.example.advt.domain.Advt;
        import com.example.advt.domain.Category;
        import com.example.advt.domain.Subcategory;
        import com.example.advt.repos.AdvtRepository;
        import com.example.advt.repos.CategoryRepository;
        import com.example.advt.repos.SubcategoryRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *30.09.2019
 */
@Controller
@RequestMapping("subcategory")
public class SubCategoryController {
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AdvtRepository advtRepository;

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody
    boolean editSubcategory(@RequestBody SubCategoryDAO subCategoryDAO) {
        Subcategory oldSubcategory = subcategoryRepository.findById(subCategoryDAO.getId());
        oldSubcategory.setName(subCategoryDAO.getName());
        subcategoryRepository.save(oldSubcategory);
        return true;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    boolean addSubcategory(@RequestBody SubCategoryDAO subCategoryDAO) {
        if (subCategoryDAO.getCategory() > 0 && subCategoryDAO.getName() != "") {
            String name = "Інше";
            Subcategory newSubcategory = new Subcategory();
            int i = subCategoryDAO.getCategory();
            Subcategory oldSubcategory = subcategoryRepository.findByCategoryIdAndName(i, name);
            oldSubcategory.setName(subCategoryDAO.getName());
            subcategoryRepository.save(oldSubcategory);
            newSubcategory.setName("Інше");
            Category cat = categoryRepository.findById(i);
            newSubcategory.setCategory(cat);
            subcategoryRepository.save(newSubcategory);
            return true;
        } else
            return false;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    boolean deleteSubcategory(@RequestBody int id) {
        if (id > 0) {
            Subcategory oldSubcategory = subcategoryRepository.findById(id);
            if (!oldSubcategory.getName().equals("Not name")) {
                List<Advt> sdvtList = advtRepository.findBySubcategory_Id(id);

                Subcategory subcategory = subcategoryRepository.findByCategoryIdAndName(oldSubcategory.getCategory().getId(), "Not name");
                if (sdvtList.size() > 0) {
                    for (Advt advt : sdvtList) {
                        advt.setSubcategory(subcategory);

                        advtRepository.save(advt);
                    }
                }
                subcategoryRepository.delete(oldSubcategory);
                return true;

            }

            return false;
        }
        return false;
    }

}
