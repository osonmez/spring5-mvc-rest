package guru.springfamework.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService{	
	
	private final VendorMapper vendorMapper;
	private final VendorRepository vendorRepository;
	
	public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
		super();
		this.vendorMapper = vendorMapper;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public VendorDTO getVendorById(Long id) {
		return vendorMapper.vendorToVendorDTO(vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
	}

	@Override
	public VendorListDTO getAllVendors() {
		List<VendorDTO> vendors = vendorRepository.findAll().stream().map(v -> vendorMapper.vendorToVendorDTO(v)).collect(Collectors.toList());
		return new VendorListDTO(vendors);
	}

	@Override
	public VendorDTO createNewVendor(VendorDTO vendorDTO) {
		Vendor savedVendor = vendorRepository.save(vendorMapper.vendorDTOToVendor(vendorDTO));
		VendorDTO savedDTO = vendorMapper.vendorToVendorDTO(savedVendor);
		savedDTO.setVendorUrl(getCustomerUrl(savedVendor.getId()));
		
		return savedDTO;
	}

	@Override
	public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
		Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
		vendor.setId(id);
		
		Vendor savedVendor = vendorRepository.save(vendor);
		VendorDTO savedDTO = vendorMapper.vendorToVendorDTO(savedVendor);
		savedDTO.setVendorUrl(getCustomerUrl(savedVendor.getId()));
		
		return savedDTO;
	}

	@Override
	public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
		Vendor vendor = vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		if(vendorDTO.getName() != null) {
			vendor.setName(vendorDTO.getName());
		}
		VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
		returnDTO.setVendorUrl(getCustomerUrl(id));
		
		return returnDTO;
	}

	@Override
	public void deleteVendorById(Long id) {
		vendorRepository.deleteById(id);		
	}
	
	private String getCustomerUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
	
	
	
	

}
